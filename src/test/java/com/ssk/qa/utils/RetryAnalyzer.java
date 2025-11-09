package com.ssk.qa.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger log = LogManager.getLogger(RetryAnalyzer.class);

    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            log.warn("🔁 Retrying test: {} | Attempt {}", result.getName(), (retryCount + 1));
            return true;
        } else {
            log.error("❌ Test failed after {} attempts: {}", (retryCount + 1), result.getName());
        }
        return false;
    }
}
