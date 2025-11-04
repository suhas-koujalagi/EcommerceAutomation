package com.ssk.qa.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class SetupTest extends BaseTest {
	
	@BeforeClass
	public void setup() {
		initializeBrowser();
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
}
