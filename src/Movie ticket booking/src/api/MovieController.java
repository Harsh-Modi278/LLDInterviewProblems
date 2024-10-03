package api;

import models.Movie;
import services.MovieService;

public class MovieController {
    final private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public Movie createMovie(final String movieName) {
        return movieService.createMovie(movieName);
    }
}
