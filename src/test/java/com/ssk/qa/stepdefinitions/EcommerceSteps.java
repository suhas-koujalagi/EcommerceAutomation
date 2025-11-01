package com.ssk.qa.stepdefinitions;

import com.ssk.qa.pages.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class EcommerceSteps {

    WebDriver driver = Hooks.driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @Given("user is logged in to SauceDemo")
    public void user_is_logged_in_to_sauce_demo() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
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

    @Given("user has a product in the cart")
    public void user_has_a_product_in_the_cart() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
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
        checkoutPage.enterUserDetails("Suhas", "SK", "560001");
        checkoutPage.completeCheckout();
    }

    @Then("order should be placed successfully")
    public void order_should_be_placed_successfully() {
        Assert.assertTrue(checkoutPage.isOrderSuccessful(), "Order not placed!");
    }

    @Given("user is on the Products page")
    public void user_is_on_the_products_page() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }

    @When("user clicks logout")
    public void user_clicks_logout() {
        try {
            driver.findElement(By.id("react-burger-menu-btn")).click();
            Thread.sleep(1000); // Wait for menu animation
            driver.findElement(By.id("logout_sidebar_link")).click();
        } catch (Exception e) {
            throw new RuntimeException("Logout failed - menu not found", e);
        }
    }

    @Then("user should be redirected to login page")
    public void user_should_be_redirected_to_login_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "Logout failed!");
    }
}
