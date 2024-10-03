package tests.scenarios;

import api.*;
import models.Screen;
import models.Seat;
import models.Theatre;
import providers.*;
import services.*;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    protected MovieController movieController;
    protected TheatreController theatreController;
    protected ScreenController screenController;
    protected BookingController bookingController;
    protected ShowController showController;
    protected PaymentsController paymentsController;

    protected void setupControllers(Integer lockTimeout, Integer allowedRetries) {
        final ISeatLockProvider seatLockProvider = new InMemorySeatLockProvider(lockTimeout);

        final MovieService movieService = new MovieService();
        final TheatreService theatreService = new TheatreService();
        final ScreenService screenService = new ScreenService(theatreService);
        final BookingService bookingService = new BookingService(seatLockProvider);
        final SeatService seatService = new SeatService(bookingService, screenService, seatLockProvider);
        final ShowService showService = new ShowService();
        final PaymentService paymentsService = new PaymentService(seatLockProvider, allowedRetries);

        movieController = new MovieController(movieService);
        theatreController = new TheatreController(theatreService);
        screenController = new ScreenController(seatService, screenService);
        bookingController = new BookingController(bookingService, showService);
        showController = new ShowController(showService, movieService, screenService, seatService);
        paymentsController = new PaymentsController(paymentsService, bookingService);
    }

    protected List<Seat> createSeats(TheatreController theatreController, String screenId, Integer numSeatsInRow) {
        List<Seat> seats = new ArrayList<>();
            for (int seatNo = 0; seatNo < numSeatsInRow; seatNo++) {
                Seat seat = screenController.addSeatInScreen(seatNo, screenId);
                seats.add(seat);
            }
        return seats;
    }

    protected Screen setupScreen() {
        final Theatre theatre = theatreController.createTheatre("Theatre 1");
        return screenController.createScreenInTheatre("Screen 1", theatre.getId());
    }
}
