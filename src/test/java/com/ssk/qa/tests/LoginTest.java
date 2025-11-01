package com.ssk.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ssk.qa.base.BaseTest;
import com.ssk.qa.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        boolean result = loginPage.isLoginSuccessful();
        Assert.assertTrue(result, "Login failed! Products page not displayed.");
    }
}
