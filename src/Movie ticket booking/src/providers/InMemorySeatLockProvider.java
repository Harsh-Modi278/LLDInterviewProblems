package providers;

import exceptions.*;
import models.Seat;
import models.SeatLock;
import models.Show;
import services.SeatService;

import java.util.*;

public class InMemorySeatLockProvider implements ISeatLockProvider{
    private final Map<Show, Map<Seat, SeatLock>> locks;
    private final Integer lockTimeout;

    public InMemorySeatLockProvider(final Integer lockTimeout) {
        this.locks = new HashMap<>();
        this.lockTimeout = lockTimeout;
    }

    @Override
    synchronized public void lockSeats(Show show, List<Seat> seats, String user) {
        // check if any of the seat is locked
        for (Seat seat : seats) {
            if (isSeatLocked(seat, show)) {
                throw new SeatTemporaryUnavailableException("seat is temporarily unavailable");
            }
        }

        // none of the seats is locked, so now lock the seats
        for (Seat seat : seats) {
            lockSeat(seat, show, user, lockTimeout);
        }
    }

    @Override
    synchronized public void unlockSeats(Show show, List<Seat> seats, String user) {
        for (Seat seat : seats) {
            if (!validateLock(show, seat, user)) {
                continue;
            }
            unlockSeat(seat, show);
        }
    }

    @Override
    public Boolean validateLock(Show show, Seat seat, String user) {
        return isSeatLocked(seat, show) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        List<Seat> seats = new ArrayList<>();
        if (locks.get(show) != null) {
            for (Seat seat : locks.get(show).keySet()) {
                if (isSeatLocked(seat, show)) {
                    seats.add(seat);
                }
            }
        }

        return seats;
    }

    private void lockSeat(final Seat seat, final Show show, final String user, final Integer lockTimeoutInSeconds) {
        if (!locks.containsKey(show)) {
            locks.put(show, new HashMap<>());
        }

        final SeatLock seatLock = new SeatLock(seat, show, lockTimeoutInSeconds, new Date(), user);
        locks.get(show).put(seat, seatLock);
    }

    private void unlockSeat(final Seat seat, final Show show) {
        if (!locks.containsKey(show)) {
            return;
        }

        locks.get(show).remove(seat);
    }

    private Boolean isSeatLocked(final Seat seat, final Show show) {
        // does lock exists on this seat for the given show
        if (locks.containsKey(show) && locks.get(show).containsKey(seat)) {
            // lock exists, is it expired or not
            return !locks.get(show).get(seat).isLockExpired();
        }

        return false;
    }
}
