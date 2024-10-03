package services;

import exceptions.NotFoundExceptions;
import models.Screen;
import models.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {
    private final Map<String, Theatre> theatreMap;

    public TheatreService() {
        theatreMap = new HashMap<>();
    }

    public Theatre createTheatre(final String theatreName){
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatreMap.put(theatreId, theatre);

        return theatre;
    }

    public Theatre getTheatre(final String theatreId){
        if (!theatreMap.containsKey(theatreId)){
            throw new NotFoundExceptions("theatre not found");
        }
        return theatreMap.get(theatreId);
    }

    public void addScreen(Screen screen, final String theatreId) {
        Theatre theatre = getTheatre(theatreId);
        theatre.addScreen(screen);
    }
}
