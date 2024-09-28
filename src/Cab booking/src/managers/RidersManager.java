package managers;

import exceptions.RiderAlreadyExistsException;
import exceptions.RiderNotFoundException;
import models.Rider;

import java.util.HashMap;
import java.util.Map;

public class RidersManager {
    private Map<String, Rider> riderMap;
    public RidersManager() {
        riderMap = new HashMap<>();
    }

    public void addRider(Rider rider) {
        if (riderMap.containsKey(rider.getId())) {
            throw new RiderAlreadyExistsException();
        }

        riderMap.put(rider.getId(), rider);
    }

    public Rider getRider(String id) {
        if (!riderMap.containsKey(id)) {
            throw new RiderNotFoundException();
        }
        return riderMap.get(id);
    }
}
