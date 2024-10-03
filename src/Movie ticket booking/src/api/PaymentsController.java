package api;

import services.BookingService;
import services.PaymentService;

public class PaymentsController {
    private final PaymentService paymentService;
    private final BookingService bookingService;

    public PaymentsController(PaymentService paymentService, BookingService bookingService) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
    }

    public void paymentSuccess(final String bookingId, final String user) {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
    }

    public void paymentFailed(final String bookingId, final String user) {
        paymentService.processFailedPayment(bookingService.getBooking(bookingId), user);
    }
}
