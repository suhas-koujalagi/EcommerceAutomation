package com.ssk.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Centralized Logger Manager.
 * Used to get Log4j loggers for any class without repeating code.
 */
public class LoggerManager {

    private LoggerManager() {
        // private constructor to prevent instantiation
    }

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}

