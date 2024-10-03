package api;

import models.*;
import services.BookingService;
import services.ShowService;

import java.util.List;

public class BookingController {
    private final BookingService bookingService;
    private final ShowService showService;

    public BookingController(BookingService bookingService, ShowService showService) {
        this.bookingService = bookingService;
        this.showService = showService;
    }


    public Booking createBooking(final String userId, final String showId, List<Seat> seats) {
        final Show show = showService.getShow(showId);
        return bookingService.createBooking(userId, show, seats);
    }
}
