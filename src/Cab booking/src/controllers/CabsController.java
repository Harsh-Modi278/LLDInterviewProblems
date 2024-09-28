package controllers;

import managers.CabsManager;
import models.Cab;
import models.Location;
import models.ResponseEntity;

public class CabsController {
    private final CabsManager cabsManager;

    public CabsController(CabsManager cabsManager) {
        this.cabsManager = cabsManager;
    }

    public String registerCab(final String cabId, final String driverName) {
        cabsManager.addCab(new Cab(cabId, driverName));
        return ResponseEntity.getOK_RESPONSE();
    }

    public String updateCabAvailability(final String cabId, final Boolean newAvailability) {
        cabsManager.getCab(cabId).setIsAvailable(newAvailability);
        return ResponseEntity.getOK_RESPONSE();
    }

    public String endTrip(final String cabId) {
        cabsManager.endTrip(cabId);
        return ResponseEntity.getOK_RESPONSE();
    }

    public String updateCabLocation(final String cabId, final Double locX, final Double locY) {
        cabsManager.getCab(cabId).setCurrentLocation(new Location(locX, locY));
        return ResponseEntity.getOK_RESPONSE();
    }
}
