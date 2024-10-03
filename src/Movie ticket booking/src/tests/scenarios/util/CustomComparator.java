package tests.scenarios.util;

import models.Seat;

import java.util.Comparator;

public class CustomComparator implements Comparator<Seat> {
    public int compare(Seat seat1, Seat seat2) {
        return seat1.getSeatNumber() - seat2.getSeatNumber();
    }
}
