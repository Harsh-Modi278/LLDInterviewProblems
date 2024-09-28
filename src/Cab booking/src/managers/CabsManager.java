package managers;

import exceptions.CabAlreadyExistsException;
import exceptions.CabNotFoundException;
import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabsManager {
    private Map<String, Cab> cabs;

    public CabsManager() {
        cabs = new HashMap<>();
    }

    public void addCab(Cab cab) {
        if (cabs.containsKey(String.valueOf(cab.getId()))) {
            throw new CabAlreadyExistsException();
        }
        cabs.put(cab.getId(), cab);
    }

    public Cab getCab(String id) {
        if (!cabs.containsKey(id)) {
            throw new CabNotFoundException();
        }
        return cabs.get(id);
    }

    public List<Cab> getAllCabs(Location riderLocation, Double radius) {
        List<Cab> availableCabs = new ArrayList<>();
        for (Cab cab: cabs.values()) {
            if (cab != null && cab.getIsAvailable() && cab.getCurrentTrip() == null && (cab.getCurrentLocation().distance(riderLocation) <= radius)) {
                availableCabs.add(cab);
            }
        }

        return availableCabs;
    }

    public void endTrip(final String id) {
        Cab cab = getCab(id);
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }
}
