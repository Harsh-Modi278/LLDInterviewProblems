package providers;

import models.*;

import java.util.List;

public interface ISeatLockProvider {
    void lockSeats(Show show, List<Seat> seats, String user);
    void unlockSeats(Show show, List<Seat> seats, String user);
    Boolean validateLock(Show show, Seat seat, String user);
    List<Seat> getLockedSeats(Show show);
}
