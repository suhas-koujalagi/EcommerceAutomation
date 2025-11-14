package com.ssk.qa.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ssk.qa.base.SetupTest;
import com.ssk.qa.pages.CartPage;
import com.ssk.qa.pages.CheckoutPage;
import com.ssk.qa.pages.LoginPage;
import com.ssk.qa.pages.LogoutPage;
import com.ssk.qa.pages.ProductsPage;
import com.ssk.qa.utils.ExcelUtils;
import org.apache.logging.log4j.Logger;
import com.ssk.qa.utils.LoggerManager;
import com.ssk.qa.utils.ConfigReader;

@Listeners(com.ssk.qa.utils.TestListener.class)
public class EndToEndTest extends SetupTest {

	private String username;
	private String password;

	//using the logger from the utils class LoggerManager
	private static final Logger logger = LoggerManager.getLogger(EndToEndTest.class);

	//default constructor
	public EndToEndTest() {

	}

	public EndToEndTest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	//Loading data from excel sheet for data driven testing
	@DataProvider(name = "loginData")
	public static Object[][] getLoginData() {
		String path = "src/test/resources/testdata/LoginData.xlsx";
		return ExcelUtils.getExcelData(path, "LoginData");
	}

	@Factory(dataProvider = "loginData")
	public static Object[] createInstances(String username, String password) {
		return new Object[]{ new EndToEndTest(username, password) };
	}

	@Test(description = "Full end-to-end workflow for each user", retryAnalyzer = com.ssk.qa.utils.RetryAnalyzer.class)
	public void verifyEndToEndTestWorkflow() {

		logger.info("Thread={} | Browser={}", Thread.currentThread().getName(),
				System.getProperty("selenium.browser", "from-xml-or-config"));


		logger.info(">> Starting test for user: {}", username);

		driver.get(ConfigReader.getProperty("baseUrl"));
		logger.info(">> Navigated to SauceDemo homepage");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		logger.debug(">> Entered credentials — Username: [{}], Password: [{}]", username, password);

		loginPage.clickLogin();

		// Handle locked_out_user
		if (username.equalsIgnoreCase("locked_out_user")) {
			boolean loginSuccess = loginPage.isLoginSuccessful();
			if (loginSuccess) {
				logger.error(">> Locked user was able to log in! {}", username);
				Assert.fail("Login should fail for locked_out_user but passed!");
			} else {
				logger.warn(">> Locked user detected and correctly blocked: {}", username);
			}
			return; // stop here for locked user
		}

		// Proceed for valid users
		Assert.assertTrue(loginPage.isLoginSuccessful(), "> Login failed unexpectedly for: " + username);
		logger.info(">> Login successful for user: {}", username);

		// Add to Cart
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.addProductToCart();
		Assert.assertTrue(productsPage.isProductAdded(), ">> Product not added to cart.");
		logger.info(">> Product successfully added to cart for {}", username);

		// Checkout
		productsPage.openCart();
		CartPage cartPage = new CartPage(driver);
		cartPage.clickCheckout();
		logger.info(">> Proceeding to checkout page");

		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.enterUserDetails(username, username.substring(4), "India");
		checkoutPage.completeCheckout();
		Assert.assertTrue(checkoutPage.isOrderSuccessful(),
				">> Order was not completed successfully for: " + username);
		logger.info(">> Order completed successfully for {}", username);

		// Logout
		LogoutPage logoutPage = new LogoutPage(driver);
		logoutPage.openOptionsMenu();
		logoutPage.clickLogoutButton();
		logger.info(">> Logout action performed for {}", username);

		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("saucedemo.com"),
				">> Logout did not redirect to login page for " + username);
		logger.info(">> Logout successful for {}", username);
	}
}
