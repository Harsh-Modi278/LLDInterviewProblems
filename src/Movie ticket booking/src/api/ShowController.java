package api;

import models.*;
import services.MovieService;
import services.ScreenService;
import services.SeatService;
import services.ShowService;
import java.util.Date;
import java.util.List;

public class ShowController {
    private final ShowService showService;
    private final MovieService movieService;
    private final ScreenService screenService;
    private final SeatService seatService;

    public ShowController(ShowService showService, MovieService movieService, ScreenService screenService, SeatService seatService) {
        this.showService = showService;
        this.movieService = movieService;
        this.screenService = screenService;
        this.seatService = seatService;
    }

    public Show createShow(final String movieId, final String screenId, Date startTime, Integer durationInSeconds) {
        final Movie movie = movieService.getMovie(movieId);
        final Screen screen = screenService.getScreen(screenId);

        return showService.createShow(movie, screen, startTime, durationInSeconds);
    }

    public List<Seat> getAvailableSeats(final String showId) {
        final Show show = showService.getShow(showId);
        return seatService.getAvailableSeats(show);
    }
}
