package com.ssk.qa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class LoginPage {
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "user-name")
	private WebElement usernameField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	@FindBy(xpath = "//span[text()='Products']")
	private WebElement productsTitle;

	@FindBy(css = "h3[data-test='error']")
	private WebElement errorBanner;

	public LoginPage(WebDriver driver) {
		this.setDriver(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {
		wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
		passwordField.sendKeys(password);
	}

	public void clickLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}

	public boolean isLoginSuccessful() {
		try {
			wait.until(ExpectedConditions.or(
					ExpectedConditions.visibilityOf(productsTitle),
					ExpectedConditions.visibilityOf(errorBanner)
					));
		} catch (TimeoutException e) {
			return false;
		}

		if (isElementVisible(productsTitle)) {
			System.out.println(">> Login Successful!");
			return true;
		} else if (isElementVisible(errorBanner)) {
			System.out.println(">> Login Failed: " + errorBanner.getText());
		}
		return false;
	}

	private boolean isElementVisible(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	//additional getter and setter for driver instance
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
