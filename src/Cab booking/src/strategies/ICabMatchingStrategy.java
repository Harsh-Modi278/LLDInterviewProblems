package strategies;

import models.*;

import java.util.List;

public interface ICabMatchingStrategy {
    Cab matchCabToRider(Rider rider, List<Cab> cabs, Location pickupLocation, Location dropOffLocation);
}
