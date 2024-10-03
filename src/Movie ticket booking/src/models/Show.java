package models;

import java.util.Date;

public class Show {
    private final String id;
    private final Movie moviePlaying;
    private final Screen screen;
    private final Date startTime;
    private final Integer durationInSeconds;

    public Show(String id, Movie moviePlaying, Screen screen, Date startTime, Integer durationInSeconds) {
        this.id = id;
        this.moviePlaying = moviePlaying;
        this.screen = screen;
        this.startTime = startTime;
        this.durationInSeconds = durationInSeconds;
    }

    public String getId() {
        return id;
    }

    public Movie getMoviePlaying() {
        return moviePlaying;
    }

    public Screen getScreen() {
        return screen;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }
}
