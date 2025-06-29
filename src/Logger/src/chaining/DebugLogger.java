package chaining;

import observer.LoggerObservable;

public class DebugLogger extends AbstractLogger{
    public DebugLogger(LoggerObservable observable) {
        super(LogType.DEBUG, observable);
    }

    @Override
    public void write(String message) {
        observable.notifyAll(LogType.DEBUG, "DEBUG: " + message);
    }
}
