package models;

import java.time.Instant;
import java.util.Date;

public class SeatLock {
    private Seat seat;
    private Show show;
    private Integer timeoutInSeconds;
    private Date lockedTime;
    private String lockedBy;

    public SeatLock(Seat seat, Show show, Integer timeoutInSeconds, Date lockedTime, String lockedBy) {
        this.seat = seat;
        this.show = show;
        this.timeoutInSeconds = timeoutInSeconds;
        this.lockedTime = lockedTime;
        this.lockedBy = lockedBy;
    }

    public Boolean isLockExpired() {
        final Instant lockedOn = this.lockedTime.toInstant().plusSeconds(this.timeoutInSeconds);
        final Instant currentInstant = Instant.now();

        return lockedOn.isBefore(currentInstant);
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public Seat getSeat() {
        return seat;
    }

    public Show getShow() {
        return show;
    }
}
