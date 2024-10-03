package api;

import models.Theatre;
import services.TheatreService;

public class TheatreController {
    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    public Theatre createTheatre(final String theatreName) {
        return theatreService.createTheatre(theatreName);
    }
}
