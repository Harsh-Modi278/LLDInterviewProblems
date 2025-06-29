package observer.impl;

import observer.LoggerObserver;

public class ConsoleLogger implements LoggerObserver {
    @Override
    public void log(String message) {
        System.out.println("Console: " + message);
    }
}
