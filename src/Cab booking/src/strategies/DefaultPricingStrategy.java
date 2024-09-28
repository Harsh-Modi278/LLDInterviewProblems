package strategies;

import models.Location;

public class DefaultPricingStrategy implements IPricingStrategy{
    public static final Double PER_KM_RATE = 10.0;

    public Double findPrice(Location pickupLocation, Location dropOffLocation) {
        return pickupLocation.distance(dropOffLocation) * PER_KM_RATE;
    }
}
