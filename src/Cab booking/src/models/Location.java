package models;
import static java.lang.Math.sqrt;

public class Location {
    private final Double x, y;

    public Location(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double distance(Location targetLocation) {
        return sqrt( (x-targetLocation.getX()) * (x - targetLocation.getX()) + (y-targetLocation.getY())* (y-targetLocation.getY()));
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
