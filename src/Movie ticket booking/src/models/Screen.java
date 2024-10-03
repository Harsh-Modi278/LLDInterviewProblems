package models;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String id;
    private final String name;
    private final List<Seat> seats;
    private final Theatre theatre;

    public Screen(String id, String name, Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        this.seats = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void addSeats(List<Seat> seats) {
        this.seats.addAll(seats);
    }

    public Theatre getTheatre() {
        return theatre;
    }
}
