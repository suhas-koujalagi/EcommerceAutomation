package com.ssk.qa.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

//utilize the BaseTest.java to implement the browser closing and resource claeUp after each testCase
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
