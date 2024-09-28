package controllers;

import managers.RidersManager;
import managers.TripsManager;
import models.Location;
import models.ResponseEntity;
import models.Rider;
import models.Trip;

import java.util.List;

public class RidersController {
    private final RidersManager ridersManager;
    private final TripsManager tripsManager;

    public RidersController(RidersManager ridersManager, TripsManager tripsManager) {
        this.ridersManager = ridersManager;
        this.tripsManager = tripsManager;
    }

    public String registerRider(final String riderId, final String riderName) {
        ridersManager.addRider(new Rider(riderId, riderName));
        return ResponseEntity.getOK_RESPONSE();
    }

    public String bookCab(final String riderId, Double pickupX, Double pickupY, Double dropX, Double dropY) {
        Location pickup = new Location(pickupX, pickupY);
        Location drop = new Location(dropX, dropY);

        Rider rider = ridersManager.getRider(riderId);
        tripsManager.createTrip(rider, pickup, drop);
        return ResponseEntity.getOK_RESPONSE();
    }

    public List<Trip> fetchHistory(final String riderId) {
        return tripsManager.getTripsForRider(ridersManager.getRider(riderId));
    }
}
