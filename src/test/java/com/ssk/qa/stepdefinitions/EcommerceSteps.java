package com.ssk.qa.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.ssk.qa.pages.CartPage;
import com.ssk.qa.pages.CheckoutPage;
import com.ssk.qa.pages.LoginPage;
import com.ssk.qa.pages.LogoutPage;
import com.ssk.qa.pages.ProductsPage;
import com.ssk.qa.utils.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EcommerceSteps {

    WebDriver driver = Hooks.driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    // ================= LOGIN SCENARIO =================
    @Given("user launches the browser")
    public void user_launches_the_browser() {
        driver = Hooks.driver; // Browser already launched by Hooks @Before
        System.out.println("Browser launched successfully.");
    }

    @Given("navigates to SauceDemo login page")
    public void navigates_to_sauce_demo_login_page() {
        driver.get(ConfigReader.getProperty("baseUrl"));
        loginPage = new LoginPage(driver);
    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
    }

    @When("clicks on login button")
    public void clicks_on_login_button() {
        loginPage.clickLogin();
    }

    @Then("user should be redirected to Products page")
    public void user_should_be_redirected_to_products_page() {
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isOnProductPage(), "User not redirected to Products page!");
    }

    // ================= ADD TO CART SCENARIO =================
    @Given("user is logged in to SauceDemo")
    public void user_is_logged_in_to_sauce_demo() {
        driver.get(ConfigReader.getProperty("baseUrl"));
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLogin();
        productsPage = new ProductsPage(driver);
    }

    @When("user adds a product to the cart")
    public void user_adds_a_product_to_the_cart() {
        productsPage.addProductToCart();
    }

    @Then("product should appear in the cart")
    public void product_should_appear_in_the_cart() {
        Assert.assertTrue(productsPage.isProductAdded(), "Product not added to cart!");
    }

    // ================= CHECKOUT SCENARIO =================
    @Given("user has a product in the cart")
    public void user_has_a_product_in_the_cart() {
        driver.get(ConfigReader.getProperty("baseUrl"));
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLogin();

        productsPage = new ProductsPage(driver);
        productsPage.addProductToCart();
        productsPage.openCart();
        cartPage = new CartPage(driver);
    }

    @When("user proceeds to checkout and enters details")
    public void user_proceeds_to_checkout_and_enters_details() {
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterUserDetails("Suhas", "Koujalagi", "560001");
        checkoutPage.completeCheckout();
    }

    @Then("order should be placed successfully")
    public void order_should_be_placed_successfully() {
        Assert.assertTrue(checkoutPage.isOrderSuccessful(), "Order not placed!");
    }

    // ================= LOGOUT SCENARIO =================
    @Given("user is on the Products page")
    public void user_is_on_the_products_page() {
        driver.get(ConfigReader.getProperty("baseUrl"));
        loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLogin();
    }

    @When("user clicks logout")
    public void user_clicks_logout() {
        try {
            LogoutPage logoutPage = new LogoutPage(driver);
            logoutPage.openOptionsMenu();
            Thread.sleep(2000);
            logoutPage.clickLogoutButton();
        } catch (Exception e) {
            throw new RuntimeException("Logout failed - menu not found", e);
        }
    }

    @Then("user should be redirected to login page")
    public void user_should_be_redirected_to_login_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "Logout failed!");
    }
}
