package models;

enum TripStatus {
    IN_PROGRESS,
    COMPLETED,
}

public class Trip {
    private final Rider rider;
    private final Cab cab;
    private TripStatus status;
    private Double price;
    private final Location pickupLocation;
    private final Location dropOffLocation;

    public Trip(Rider rider, Cab cab, Double price, Location pickupLocation, Location dropOffLocation) {
        this.rider = rider;
        this.cab = cab;
        this.price = price;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.status = TripStatus.IN_PROGRESS;
    }

    public void endTrip() {
        this.status = TripStatus.COMPLETED;
    }

    public Rider getRider() {
        return rider;
    }

    public Cab getCab() {
        return cab;
    }

    public Double getPrice() {
        return price;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }
}
