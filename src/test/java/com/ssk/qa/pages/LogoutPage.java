package com.ssk.qa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class LogoutPage {
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuButton;

	@FindBy(id = "logout_sidebar_link")
	private WebElement logoutButton;

	public LogoutPage(WebDriver driver) {
		this.setDriver(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	public void openOptionsMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
	}

	public void clickLogoutButton() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
	}

	//additional getter and setter for driver instance
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
