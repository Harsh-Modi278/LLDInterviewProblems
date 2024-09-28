package strategies;

import models.Location;

public interface IPricingStrategy {
    Double findPrice(Location pickUpLocation, Location dropOffLocation);
}
