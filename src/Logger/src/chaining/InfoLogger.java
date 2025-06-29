package chaining;

import observer.LoggerObservable;

public class InfoLogger extends AbstractLogger{
    public InfoLogger(LoggerObservable observable) {
        super(LogType.INFO, observable);
    }

    @Override
    public void write(String message) {
        observable.notifyAll(LogType.INFO, "INFO: " + message);
    }
}
