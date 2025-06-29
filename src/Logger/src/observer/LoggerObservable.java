package observer;

import chaining.LogType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerObservable {
    private String name;

    public LoggerObservable(String name) {
        this.name = name;
    }

    Map<LogType, List<LoggerObserver>> logTypeToObseversMap = new HashMap<>();

    public void addObserver(LogType logType, LoggerObserver newLoggerObserver) {
        List<LoggerObserver> observers = logTypeToObseversMap.getOrDefault(logType, new ArrayList<>());
        observers.add(newLoggerObserver);
        logTypeToObseversMap.put(logType, observers);
    }

    // notify all observers for logType
    public void notifyAll(LogType logType, String message) {
        List<LoggerObserver> observers = logTypeToObseversMap.getOrDefault(logType, new ArrayList<>());
        observers.forEach(obs -> obs.log(message));
    }
}
