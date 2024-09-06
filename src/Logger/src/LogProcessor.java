public class LogProcessor {
    public static int INFO = 0;
    public static int DEBUG = 1;
    public static int ERROR = 2;

    LogProcessor nextLogProcessor;

    LogProcessor(LogProcessor logProcessor) {
        this.nextLogProcessor = logProcessor;
    }

    public void log(int logLevel, String message) {
        if (nextLogProcessor != null) {
            nextLogProcessor.log(logLevel, message);
        }
    }
}
