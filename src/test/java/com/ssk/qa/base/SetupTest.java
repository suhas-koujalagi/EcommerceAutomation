package com.ssk.qa.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class SetupTest extends BaseTest {

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional String browser) {
      // Pass browser from XML (or null if not provided)
      initializeBrowser(browser);
    }

    @AfterMethod
    public void tearDown() {
        super.tearDown();
    }
}
