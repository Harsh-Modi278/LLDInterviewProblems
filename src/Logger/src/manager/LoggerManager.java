package manager;

import chaining.*;
import observer.LoggerObservable;
import observer.LoggerObserver;

public class LoggerManager {
    private final AbstractLogger loggerChain;
    private final LoggerObservable observable = new LoggerObservable("my observable");

    public LoggerManager() {
        AbstractLogger infoLogger = new InfoLogger(observable);
        AbstractLogger errorLogger = new ErrorLogger(observable);
        AbstractLogger debugLogger = new DebugLogger(observable);

        // setup chains
        infoLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);

        this.loggerChain = infoLogger;
    }

    public AbstractLogger getLoggerChain() {
        return this.loggerChain;
    }

    public void addObserver(LogType logLevel, LoggerObserver observer) {
        observable.addObserver(logLevel, observer);
    }
}
