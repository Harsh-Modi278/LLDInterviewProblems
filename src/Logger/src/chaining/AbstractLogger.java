package chaining;

import observer.LoggerObservable;

public abstract class AbstractLogger {
    private AbstractLogger nextLogger;
    private final LogType logLevel;
    protected final LoggerObservable observable;

    public AbstractLogger(LogType logLevel, LoggerObservable observable) {
        this.observable = observable;
        this.logLevel = logLevel;
    }

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(LogType logLevel, String message) {
        if (this.logLevel == logLevel) {
            write(message);
        }

        if (nextLogger != null) {
            nextLogger.log(logLevel, message);
        }
    }

    public abstract void write(String message);
}
