package services;

import exceptions.AlreadyExistsException;
import exceptions.NotFoundExceptions;
import exceptions.ScreenAlreadyOccupiedException;
import models.*;

import java.text.MessageFormat;
import java.util.*;

public class ShowService {
    private final Map<String, Show> showMap;

    public ShowService() {
        showMap = new HashMap<>();
    }

    public Show getShow(final String showId) {
        if (!showMap.containsKey(showId)) {
            throw new NotFoundExceptions("show not found");
        }

        return showMap.get(showId);
    }

    public Show createShow(Movie movie, Screen screen, Date startTime, Integer durationInSeconds) {
        if (!checkIfShowCreationAllowed(movie, screen, startTime, durationInSeconds)) {
            throw new ScreenAlreadyOccupiedException("screen already occupied");
        }

        String showId = UUID.randomUUID().toString();
        final Show show = new Show(showId, movie, screen, startTime, durationInSeconds);

        if (showMap.containsKey(showId)) {
            throw new AlreadyExistsException(new MessageFormat("Show with id: {0} already exists").format(showId));
        }

        showMap.put(showId, show);

        return show;
    }

    public List<Show> getShowsForScreen(final String screenId) {
        List<Show> showsForScreen = new ArrayList<>();
        for (Show show : showMap.values()) {
            if (show.getScreen().getId().equals(screenId)) {
                showsForScreen.add(show);
            }
        }

        return showsForScreen;
    }

    private Boolean checkIfShowCreationAllowed(Movie movie, Screen screen, Date startTime, Integer durationInSeconds) {
        return true;
    }
}
