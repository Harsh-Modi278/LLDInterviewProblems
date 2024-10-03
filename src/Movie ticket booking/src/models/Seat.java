package models;

public class Seat {
    private final String id;
    private final Integer seatNumber;

    public Seat(String id, Integer seatNumber) {
        this.id = id;
        this.seatNumber = seatNumber;
    }

    public String getId() {
        return id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }
}
