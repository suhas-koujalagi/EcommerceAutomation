package com.ssk.qa.tests;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ssk.qa.base.SetupTest;
import com.ssk.qa.pages.CartPage;
import com.ssk.qa.pages.CheckoutPage;
import com.ssk.qa.pages.LoginPage;
import com.ssk.qa.pages.LogoutPage;
import com.ssk.qa.pages.ProductsPage;
import com.ssk.qa.utils.ConfigReader;

public class EndToEndTest extends SetupTest {

	@Test(priority = 1)
	public void loginTest() {
		// ensure small global buffer in case BaseTest didn't set it
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		LoginPage loginPage = new LoginPage(driver);
		driver.get(ConfigReader.getProperty("baseUrl"));

		loginPage.enterUsername(ConfigReader.getProperty("username"));
		loginPage.enterPassword(ConfigReader.getProperty("password"));
		loginPage.clickLogin();

		// Assert landed on Products page
		Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed — Products page not visible");
	}

	// Add to Cart (depends on login)
	@Test(priority = 2, dependsOnMethods = {"loginTest"})
	public void addToCartTest() {
		ProductsPage productsPage = new ProductsPage(driver);

		// Add first product
		productsPage.addProductToCart();

		// Assert cart badge is visible and shows item(s)
		Assert.assertTrue(productsPage.isProductAdded(), "Product was not added to cart");
	}

	// Checkout (depends on addToCart)
	@Test(priority = 3, dependsOnMethods = {"addToCartTest"})
	public void checkoutTest() {
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.openCart();

		CartPage cartPage = new CartPage(driver);
		cartPage.clickCheckout();

		CheckoutPage checkoutPage = new CheckoutPage(driver);
		// fill details and complete checkout
		checkoutPage.enterUserDetails("Suhas", "SK", "560001");
		checkoutPage.completeCheckout();

		Assert.assertTrue(checkoutPage.isOrderSuccessful(), "Order was not placed successfully");
	}

	// Logout (depends on checkout)
	@Test(priority = 4, dependsOnMethods = {"checkoutTest"})
	public void logoutTest() {
		// We expect to be on the final/confirmation or products page; go to products to be safe
		driver.get("https://www.saucedemo.com/inventory.html");

		// Click menu and logout
		LogoutPage logoutPage = new LogoutPage(driver);

		logoutPage.openOptionsMenu();
		logoutPage.clickLogoutButton();  

		// Verify redirected back to login
		Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "Logout did not redirect to login page");
	}
}
