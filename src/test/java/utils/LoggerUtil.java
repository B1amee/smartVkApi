package utils;

import org.apache.log4j.Logger;

public class LoggerUtil {

    public static void info(Class clazz, String msg) {
        Logger.getLogger(clazz).info(msg);
    }

    public static void error(Class clazz, String msg, Exception e) {
        Logger.getLogger(clazz).error(msg, e);
    }
}
