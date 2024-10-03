package services;

import exceptions.NotFoundExceptions;
import models.Movie;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class MovieService {
    private final Map<String, Movie> movieMap;

    public MovieService() {
        movieMap = new TreeMap<>();
    }

    public Movie getMovie(String movieId) {
        if (!movieMap.containsKey(movieId)) {
            throw new NotFoundExceptions("movie not found");
        }

        return movieMap.get(movieId);
    }

    public Movie createMovie(String movieName) {
        final String movieId = UUID.randomUUID().toString();
        final Movie movie = new Movie(movieId, movieName);
        movieMap.put(movieId, movie);

        return movie;
    }
}
