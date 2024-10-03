package tests.scenarios;

import exceptions.SeatPermanentlyUnavailableException;
import exceptions.SeatTemporaryUnavailableException;
import models.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import tests.scenarios.util.CustomComparator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Case3Tests extends BaseTest {
    public void setUp() {
        setupControllers(10, 0);
    }

    public void runTests() {
        String user1 = "User1";
        String user2 = "User2";

        final Movie movie = movieController.createMovie("Movie 1");
        final Screen screen = setupScreen();
        final List<Seat> screen1Seats = createSeats(theatreController, screen.getId(), 10);

        final Show show = showController.createShow(movie.getId(), screen.getId(), new Date(), 2 * 60 * 60);

        List<Seat> u1AvailableSeats = showController.getAvailableSeats(show.getId());

        // Validate that seats u1 received has all screen seats
        CustomComparator seatComparator = new CustomComparator();
        u1AvailableSeats.sort(seatComparator);
        screen1Seats.sort(seatComparator);

        Assert.assertEquals(u1AvailableSeats, screen1Seats);

        List<Seat> u1SelectedSeats = new ArrayList<>();
        u1SelectedSeats.add(screen1Seats.get(0));
        u1SelectedSeats.add(screen1Seats.get(1));
        u1SelectedSeats.add(screen1Seats.get(2));
        u1SelectedSeats.add(screen1Seats.get(3));

        List<Seat> u2SelectedSeats = new ArrayList<>();
        u1SelectedSeats.add(screen1Seats.get(3));
        u1SelectedSeats.add(screen1Seats.get(4));
        u1SelectedSeats.add(screen1Seats.get(5));
        u1SelectedSeats.add(screen1Seats.get(6));

        final Booking booking = bookingController.createBooking(user1, show.getId(), u1SelectedSeats);

        Assert.assertThrows(SeatTemporaryUnavailableException.class, () -> {
            final Booking u2Booking = bookingController.createBooking(user2, show.getId(), u2SelectedSeats);
        });

        paymentsController.paymentSuccess(booking.getId(), user1);

        Assert.assertThrows(SeatPermanentlyUnavailableException.class, () -> {
            final Booking u2Booking = bookingController.createBooking(user2, show.getId(), u2SelectedSeats);
        });
    }
}
