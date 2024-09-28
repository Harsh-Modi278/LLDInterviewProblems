package strategies;

import models.Cab;
import models.Location;
import models.Rider;

import java.util.List;

public class DefaultCabMatchingStrategy implements ICabMatchingStrategy {

    @Override
    public Cab matchCabToRider(Rider rider, List<Cab> cabs, Location pickupLocation, Location dropOffLocation) {
        if (cabs.isEmpty()) {
            return null;
        }

        return cabs.getFirst();
    }
}
