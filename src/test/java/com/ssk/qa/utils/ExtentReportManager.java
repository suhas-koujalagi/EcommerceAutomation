package com.ssk.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ExtentReportManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String reportsDir = System.getProperty("user.dir") + "/reports/";
			String timestampedReport = reportsDir + "Ecommerce_Automation_Report_" + timestamp + ".html";
			String latestReport = reportsDir + "Ecommerce_Automation_Report.html"; // for Jenkins

			ExtentSparkReporter spark = new ExtentSparkReporter(timestampedReport);
			spark.config().setDocumentTitle("E-Commerce Automation Report");
			spark.config().setReportName("SauceDemo Test Execution");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Project", "E-commerce Automation");
			extent.setSystemInfo("Tester", "Suhas Koujalagi");
			extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
			extent.setSystemInfo("Operating System", "Windows 11 Home");

			// Copy to "latest" version after flush
			extent.flush();
			try {
				Files.copy(
						Paths.get(timestampedReport),
						Paths.get(latestReport),
						StandardCopyOption.REPLACE_EXISTING
						);
			} catch (IOException e) {
				System.err.println(">> Failed to copy latest Extent report: " + e.getMessage());
			}
		}
		return extent;
	}
}
