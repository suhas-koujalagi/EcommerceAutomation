package com.ssk.qa.base;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.ssk.qa.utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    // NEW: pass browserName in; fallback to config if null/empty
    protected void initializeBrowser(String browserName) {
        String browser = (browserName == null || browserName.isBlank())
                ? ConfigReader.getProperty("browser")
                : browserName;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void tearDown() {
        if (Objects.nonNull(driver)) {
            driver.quit();
        }
    }
}
