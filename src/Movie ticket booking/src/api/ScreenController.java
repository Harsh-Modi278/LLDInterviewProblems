package api;

import models.*;
import services.ScreenService;
import services.SeatService;
import services.TheatreService;

public class ScreenController {
    private final SeatService seatService;
    private final ScreenService screenService;

    public ScreenController(SeatService seatService, ScreenService screenService) {
        this.seatService = seatService;
        this.screenService = screenService;
    }

    public Screen createScreenInTheatre(final String screenName, final String theatreId) {
        return screenService.createScreenInTheatre(screenName, theatreId);
    }

    public Seat addSeatInScreen(final Integer seatNumber, final String screenId) {
        return seatService.createSeatInScreen(seatNumber, screenId);
    }
}
