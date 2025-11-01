package com.ssk.qa.stepdefinitions;

import com.ssk.qa.pages.LoginPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LoginSteps {

    LoginPage loginPage;

    @Given("user launches the browser")
    public void user_launches_the_browser() {
        loginPage = new LoginPage(Hooks.driver);
    }

    @Given("navigates to SauceDemo login page")
    public void navigates_to_sauce_demo_login_page() {
        Hooks.driver.get("https://www.saucedemo.com/");
    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
    }

    @When("clicks on login button")
    public void clicks_on_login_button() {
        loginPage.clickLogin();
    }

    @Then("user should be redirected to Products page")
    public void user_should_be_redirected_to_products_page() {
        boolean result = loginPage.isLoginSuccessful();
        Assert.assertTrue(result, "Login failed — Products page not visible!");
    }
}
