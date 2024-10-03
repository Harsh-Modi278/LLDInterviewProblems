package services;

import exceptions.BadRequestException;
import exceptions.NotFoundExceptions;
import exceptions.SeatPermanentlyUnavailableException;
import models.*;
import providers.ISeatLockProvider;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private final Map<String, Booking> showBookingsMap; // Map<bookingId, Booking>
    private final ISeatLockProvider seatLockProvider;

    public BookingService(ISeatLockProvider seatLockProvider) {
        showBookingsMap = new HashMap<>();
        this.seatLockProvider = seatLockProvider;
    }

    public Booking getBooking(final String bookingId) {
        if (!showBookingsMap.containsKey(bookingId)) {
            throw new NotFoundExceptions("Booking not found");
        }

        return showBookingsMap.get(bookingId);
    }

    public Booking createBooking(final String userId, Show show, List<Seat> seats) {
        if (isAnySeatAlreadyBooked(show, seats)) {
            throw new SeatPermanentlyUnavailableException(new MessageFormat("one or more seats from provided are unavailable").toString());
        }

        // lock the seats
        seatLockProvider.lockSeats(show, seats, userId);

        // create a new booking entry
        final String bookingId = UUID.randomUUID().toString();
        Booking newBooking = new Booking(bookingId, userId, show, seats);
        showBookingsMap.put(bookingId, newBooking);

        return newBooking;
    }

    public List<Booking> getAllBookings(final Show show) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking: showBookingsMap.values()) {
            if (booking.getShow().getId().equals(show.getId())) {
                bookings.add(booking);
            }
        }

        return bookings;
    }

    public List<Seat> getBookedSeats(final Show show) {
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeatsBooked)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void confirmBooking(final Booking booking, final String user) {
        if (!booking.getUsername().equals(user)) {
            throw new BadRequestException("Booking is created by some other user");
        }

        for (Seat seat: booking.getSeatsBooked()) {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new BadRequestException("Seat is locked by some other user");
            }
        }

        booking.confirmBooking();
    }

    private boolean isAnySeatAlreadyBooked(final Show show, final List<Seat> seatsToBeBooked) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat: seatsToBeBooked) {
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }

        return false;
    }
}
