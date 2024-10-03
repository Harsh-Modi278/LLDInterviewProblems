package services;

import exceptions.AlreadyExistsException;
import exceptions.NotFoundExceptions;
import models.*;

import java.util.*;

public class ScreenService {
    private final Map<String, Screen> screenMap;
    private final TheatreService theatreService;

    public ScreenService(TheatreService theatreService) {
        screenMap = new HashMap<>();
        this.theatreService = theatreService;
    }

    public Screen getScreen(String screenId) {
        if (!screenMap.containsKey(screenId)) {
            throw new NotFoundExceptions("screen not found");
        }
        return screenMap.get(screenId);
    }

    public Screen createScreenInTheatre(String screenName, final String theatreId) {
        Theatre theatre = theatreService.getTheatre(theatreId);
        final String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);

        if (screenMap.containsKey(screenId)) {
            throw new AlreadyExistsException("screen already exists");
        }

        screenMap.put(screenId, screen);
        theatre.addScreen(screen);

        return screen;
    }
}
