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
 * +-------------------+
 * |   LogLevel        |  <<enum>>
 * +-------------------+
 * | +INFO             |
 * | +ERROR            |
 * | +DEBUG            |
 * +-------------------+
 *
 * +---------------------------+
 * |   LoggerObserver          |  <<interface>>
 * +---------------------------+
 * | +log(message: String):void|
 * +---------------------------+
 *            ^
 *            |
 *   -----------------------
 *   |                     |
 * +-------------------+  +-------------------+
 * | ConsoleLogger     |  | FileLogger        |
 * +-------------------+  +-------------------+
 * | +log(message):void|  | -filename: String |
 * |                   |  | +log(message):void|
 * +-------------------+  +-------------------+
 *
 * +-----------------------------+
 * |   LoggerSubject             |
 * +-----------------------------+
 * | -observersMap: Map<LogLevel,|
 * |   List<LoggerObserver>>     |
 * +-----------------------------+
 * | +addObserver(level: LogLevel,|
 * |   observer: LoggerObserver):void|
 * | +notifyObservers(level: LogLevel,|
 * |   message: String):void         |
 * +-----------------------------+
 *
 * +-----------------------------+
 * |   AbstractLogger            |  <<abstract>>
 * +-----------------------------+
 * | -level: LogLevel            |
 * | -nextLogger: AbstractLogger |
 * | -subject: LoggerSubject     |
 * +-----------------------------+
 * | +setNextLogger(next: AbstractLogger):void|
 * | +log(level: LogLevel, message: String):void|
 * | #write(message: String):void (abstract)    |
 * +-----------------------------+
 *            ^
 *            |
 *   -------------------------------
 *   |             |               |
 * +------------+ +------------+ +------------+
 * | InfoLogger | | ErrorLogger| | DebugLogger|
 * +------------+ +------------+ +------------+
 * | +write()   | | +write()   | | +write()   |
 * +------------+ +------------+ +------------+
 *
 * +-----------------------------+
 * |   LoggerManager             |
 * +-----------------------------+
 * | -subject: LoggerSubject     |
 * | -loggerChain: AbstractLogger|
 * +-----------------------------+
 * | +addObserver(level: LogLevel,|
 * |   observer: LoggerObserver):void|
 * | +log(level: LogLevel, message: String):void|
 * +-----------------------------+
 */