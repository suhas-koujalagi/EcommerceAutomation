package com.ssk.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("E-commerce Automation Report");
            reporter.config().setReportName("SauceDemo Test Execution");
      
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Operating System", "Windows 11 Home");
            extent.setSystemInfo("Tested By", "Suhas Koujalagi");
        }
        return extent;
    }
}

