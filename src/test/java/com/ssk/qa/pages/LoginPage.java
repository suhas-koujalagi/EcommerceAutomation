package com.ssk.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    // Locators
    @FindBy(id="user-name")
    private WebElement usernameField;
    
    @FindBy(id="password")
    private WebElement passwordField;
   
    @FindBy(id="login-button")
    private WebElement loginButton;
    
    @FindBy(className="title")
    private WebElement productsTitle;
    

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
       loginButton.click();
    }

    public boolean isLoginSuccessful() {
       return productsTitle.isDisplayed();
    }
}

