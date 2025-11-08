package com.ssk.qa.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SetupTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        initializeBrowser();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
