package managers;

import exceptions.NoCabAvailableException;
import exceptions.TripNotFoundException;
import models.*;
import strategies.ICabMatchingStrategy;
import strategies.IPricingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripsManager {
    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;
    private Map<String, List<Trip>> tripsPerRider;
    private final CabsManager cabsManager;
    private final RidersManager ridersManager;
    private final ICabMatchingStrategy cabMatchingStrategy;
    private final IPricingStrategy pricingStrategy;

    public TripsManager(CabsManager cabsManager, RidersManager ridersManager, ICabMatchingStrategy cabMatchingStrategy, IPricingStrategy pricingStrategy) {
        tripsPerRider = new HashMap<>();
        this.cabsManager = cabsManager;
        this.ridersManager = ridersManager;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void createTrip(Rider rider, Location pickupLocation, Location dropOffLocation) {
        // get cabs which are nearer
        List<Cab> nearCabs = cabsManager.getAllCabs(pickupLocation, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);

        // get a cab
        Cab selectedCab = cabMatchingStrategy.matchCabToRider(rider, nearCabs, pickupLocation, dropOffLocation);
        if (selectedCab == null) {
            throw new NoCabAvailableException("no cab found");
        }

        // get fare
        final Double tripFare = pricingStrategy.findPrice(pickupLocation, dropOffLocation);

        // create a new trip
        Trip newTrip = new Trip(rider, selectedCab, tripFare, pickupLocation, dropOffLocation);

        // adds into tripsPerRider map
        if (!tripsPerRider.containsKey(rider.getId())) {
            tripsPerRider.put(rider.getId(), new ArrayList<>());
        }

        tripsPerRider.get(rider.getId()).add(newTrip);
        selectedCab.setCurrentTrip(newTrip);
    }

    public List<Trip> getTripsForRider(Rider rider) {
        return tripsPerRider.get(rider.getId());
    }

    public void endTrip(Cab cab) {
        if (cab.getCurrentTrip() == null) {
            throw new TripNotFoundException("no trip found");
        }

        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }
}
