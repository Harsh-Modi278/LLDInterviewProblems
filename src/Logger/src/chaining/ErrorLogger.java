package chaining;

import observer.LoggerObservable;

public class ErrorLogger extends AbstractLogger{
    public ErrorLogger(LoggerObservable observable) {
        super(LogType.ERROR, observable);
    }

    @Override
    public void write(String message) {
        observable.notifyAll(LogType.ERROR, "ERROR: " + message);
    }
}
