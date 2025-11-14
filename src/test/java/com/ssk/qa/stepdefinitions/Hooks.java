package com.ssk.qa.stepdefinitions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ssk.qa.utils.ExtentReportManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

	public static WebDriver driver;
	private static ExtentReports extent = ExtentReportManager.getInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Before
	public void setup(Scenario scenario) {
		WebDriverManager.chromedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		ExtentTest scenarioTest = extent.createTest(scenario.getName());
		test.set(scenarioTest);
	}

	@AfterStep
	public void afterEachStep(Scenario scenario) {
		if (scenario.isFailed()) {
			test.get().log(Status.FAIL, ">> Step failed: " + scenario.getName());
		} else {
			test.get().log(Status.PASS, ">> Step passed");
		}
	}

	@After
	public void tearDown(Scenario scenario) {
		if (driver != null) {
			driver.quit();
		}
	}

	//flush once after all scenarios
	@AfterAll
	public static void afterAll() {
		extent.flush();
	}
}
