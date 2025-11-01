package com.ssk.qa.stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ssk.qa.utils.ExtentReportManager;

import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    public static WebDriver driver;
    public static ExtentReports extent = ExtentReportManager.getInstance();
    public static ExtentTest test;

    @Before
    public void setup(Scenario scenario) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
     // Global implicit wait of 5 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        test = extent.createTest(scenario.getName());
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Step failed: " + scenario.getName());
        } else {
            test.log(Status.PASS, "Step passed");
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}

