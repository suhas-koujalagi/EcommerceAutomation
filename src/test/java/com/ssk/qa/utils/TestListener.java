package com.ssk.qa.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ssk.qa.base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

	private static ExtentReports extent = ExtentReportManager.getInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(
				result.getMethod().getMethodName() + " — " + result.getTestContext().getName()
				);
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTest t = test.get();
		if (t != null) {
			t.log(Status.PASS, ">> Test Passed: " + result.getMethod().getMethodName());
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTest t = test.get();
		if (t == null) {
			// Safeguard: create one if onTestStart never fired (rare but happens on fast failure)
			t = extent.createTest(result.getMethod().getMethodName());
			test.set(t);
		}

		t.log(Status.FAIL, ">> Test Failed: " + result.getMethod().getMethodName());
		t.log(Status.FAIL, result.getThrowable());

		// Capture screenshot safely
		Object testClass = result.getInstance();
		WebDriver driver = null;
		if (testClass instanceof BaseTest) {
			driver = ((BaseTest) testClass).getDriver();
		}

		if (driver != null) {
			String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
			try {
				t.addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			t.log(Status.WARNING, ">> WebDriver was null; screenshot skipped.");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTest t = test.get();
		if (t == null) {
			t = extent.createTest(result.getMethod().getMethodName());
			test.set(t);
		}
		t.log(Status.SKIP, ">> Test Skipped: " + result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	private String captureScreenshot(WebDriver driver, String testName) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
		String filePath = screenshotDir + testName + "_" + timeStamp + ".png";

		try {
			Files.createDirectories(new File(screenshotDir).toPath());
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(src.toPath(), new File(filePath).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
}
