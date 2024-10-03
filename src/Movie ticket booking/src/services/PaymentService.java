package services;

import exceptions.BadRequestException;
import models.Booking;
import providers.ISeatLockProvider;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    private final ISeatLockProvider seatLockProvider;
    private final Integer allowedRetries;
    private final Map<String, Integer> bookingFailureCount; // bookingId -> failure count

    public PaymentService(ISeatLockProvider seatLockProvider, Integer allowedRetries) {
        this.seatLockProvider = seatLockProvider;
        this.allowedRetries = allowedRetries;
        this.bookingFailureCount = new HashMap<>();
    }

    public void processFailedPayment(final Booking booking, final String user) {
        if (!booking.getUsername().equals(user)) {
            throw new BadRequestException("Booking was created by another user");
        }

        if (!bookingFailureCount.containsKey(booking.getUsername())) {
            bookingFailureCount.put(booking.getId(), 0);
        }

        final Integer currentFailures = bookingFailureCount.get(booking.getId());
        final Integer newFailures = currentFailures + 1;
        bookingFailureCount.put(booking.getId(), newFailures);

        if (newFailures > allowedRetries) {
            seatLockProvider.unlockSeats(booking.getShow(), booking.getSeatsBooked(), user);
        }
    }
}
