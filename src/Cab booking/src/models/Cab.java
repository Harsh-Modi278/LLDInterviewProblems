package models;

public class Cab {
    private final String id;
    private final String driverName;
    private Location currentLocation;
    private Boolean isAvailable = true;
    private Trip currentTrip;

    public Cab(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getDriverName() {
        return driverName;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
    }

    public Trip getCurrentTrip() {
        return currentTrip;
    }
}
