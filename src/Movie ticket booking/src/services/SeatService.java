package services;

import models.*;
import providers.ISeatLockProvider;

import java.util.*;

public class SeatService {
    final BookingService bookingService;
    final ScreenService screenService;
    final ISeatLockProvider seatLockProvider;
    private final Map<String, Seat> seatsMap;

    public SeatService(BookingService bookingService, ScreenService screenService, ISeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.screenService = screenService;
        this.seatLockProvider = seatLockProvider;
        this.seatsMap = new HashMap<>();
    }

    public List<Seat> getAvailableSeats(final Show show) {
        final List<Seat> allSeats = show.getScreen().getSeats();
        final List<Seat> unavailableSeats = getUnavailableSeats(show);

        final List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);

        return availableSeats;
    }

    private List<Seat> getUnavailableSeats(final Show show) {
        final List<Seat> bookedSeats = bookingService.getBookedSeats(show);
        final List<Seat> lockedSeats = seatLockProvider.getLockedSeats(show);

        final List<Seat> unavailableSeats = new ArrayList<Seat>();
        unavailableSeats.addAll(bookedSeats);
        unavailableSeats.addAll(lockedSeats);

        return unavailableSeats;
    }

    public Seat createSeatInScreen(final Integer seatNumber, final String screenId) {
        String seatId = UUID.randomUUID().toString();
        Seat newSeat = new Seat(seatId, seatNumber);
        seatsMap.put(seatId, newSeat);

        Screen screen = screenService.getScreen(screenId);
        screen.addSeats(new ArrayList<Seat>(List.of(newSeat)));

        return newSeat;
    }
}
