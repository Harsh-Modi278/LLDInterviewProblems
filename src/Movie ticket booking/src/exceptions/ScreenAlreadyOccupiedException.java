package exceptions;

public class ScreenAlreadyOccupiedException extends RuntimeException {
    public ScreenAlreadyOccupiedException(String message) {
        super(message);
    }
}
