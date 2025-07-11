import chaining.*;
import manager.LoggerManager;
import observer.impl.ConsoleLogger;
import observer.impl.FileLogger;

public class Main {
    public static void main(String[] args) {
        LoggerManager loggerManager = new LoggerManager();

        // Register observers for each level
        ConsoleLogger consoleLogger = new ConsoleLogger();
        loggerManager.addObserver(LogType.INFO, consoleLogger);
        loggerManager.addObserver(LogType.ERROR, consoleLogger);
        loggerManager.addObserver(LogType.DEBUG, consoleLogger);

        // Register FileLogger for all levels
        FileLogger fileLoger = new FileLogger();
        loggerManager.addObserver(LogType.INFO, fileLoger);
        loggerManager.addObserver(LogType.ERROR, fileLoger);
        loggerManager.addObserver(LogType.DEBUG, fileLoger);

        // Log messages
        loggerManager.getLoggerChain().log(LogType.INFO, "This is an info message");
        loggerManager.getLoggerChain().log(LogType.ERROR, "This is an error message");
        loggerManager.getLoggerChain().log(LogType.DEBUG, "This is a debug message");
    }
}

/***
 *
Q: How to Explain This in an Interview
 - Client requests a log operation via LoggerManager.
 - The logger chain (Chain of Responsibility) passes the message until
    the correct logger (by level) handles it.
 - The matching logger delegates output to the LoggerSubject (Observer pattern).
 - LoggerSubject notifies all registered observers (e.g., ConsoleLogger, FileLogger),
    each of which handles output independently.
 - This design is modular (easy to add new outputs or levels) and
    decoupled (loggers don’t know about output details).

 */

/**
 *
 *          +-------------------+         +----------------------+
 *         |      Main         |         |   LogType <<enum>>   |
 *         +-------------------+         +----------------------+
 *         | +main(args): void |         | INFO                 |
 *         +-------------------+         | DEBUG                |
 *         | uses                | ERROR                |
 * v                     +----------------------+
 *         +-----------------------+
 *         |   LoggerManager       |
 *         +-----------------------+
 *         | -loggerChain:         |
 *         |   AbstractLogger      |
 *         | -observable:          |
 *         |   LoggerObservable    |
 *         +-----------------------+
 *         | +addObserver(...)     |
 *         | +getLoggerChain()     |
 *         +-----------------------+
 *         | 1
 *         | composition
 *         v
 *           +---------------------------+     1      *    +---------------------------+
 *         | <<abstract>>              |<>---------o-----|   LoggerObservable        |
 *         |   AbstractLogger          | composition     |---------------------------|
 *         +---------------------------+ association    | -logTypeToObservers:      |
 *         | -logLevel: LogType        |                |   Map<LogType,List<...>>  |
 *         | -nextLogger: AbstractLogger|               +---------------------------+
 *         | -observable: LoggerObservable|             | +addObserver(...)         |
 *         +---------------------------+                | +notifyAll(...)           |
 *         | +setNextLogger(...)       |                +---------------------------+
 *         | +log(...)                 |                      | 1
 *         | #write(...)               |                      | aggregation
 *         +---------------------------+                      | *
 *         ^                                                  v
 *         | inheritance                                +---------------------------+
 *         +---------+-----------+-----------+          | <<interface>>             |
 *         |         |           |           |          |   LoggerObserver          |
 *         +-----------+ +-----------+ +-----------     +---------------------------+
 *         |InfoLogger | |ErrorLogger| |DebugLogger|    |   +log(message): void       |
 *         +-----------+ +-----------+ +-----------     +---------------------------+
 *         | +write()  | | +write()  | | +write()  |        ^             ^
 *         +-----------+ +-----------+ +-----------+        | realization | realization
 *                                        | FileLogger      |                | ConsoleLogger     |
 *                                        +-------------------+              +-------------------+
 *                                        | +log(message):void|              | +log(message):void|
 *                                        +-------------------+              +-------------------+
 *
 *
 *
 *
 *
 */