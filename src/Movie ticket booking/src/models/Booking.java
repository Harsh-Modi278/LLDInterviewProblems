package models;

import exceptions.InvalidStateException;

import java.util.List;

public class Booking {
    private final String id;
    private final String username;
    private final Show show;
    private final List<Seat> seatsBooked;
    private BookingStatus bookingStatus;

    public Booking(String id, String username, Show show, List<Seat> seatsBooked) {
        this.id = id;
        this.username = username;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.bookingStatus = BookingStatus.CREATED;
    }

    public Boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() {
        if (this.bookingStatus == BookingStatus.CREATED) {
            this.bookingStatus = BookingStatus.CONFIRMED;
            return;
        }

        throw new InvalidStateException("booking status invalid");
    }

    public void expireBooking() {
        if (this.bookingStatus == BookingStatus.CONFIRMED) {
            this.bookingStatus = BookingStatus.EXPIRED;
            return;
        }

        throw new InvalidStateException("booking status invalid");
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }
}
