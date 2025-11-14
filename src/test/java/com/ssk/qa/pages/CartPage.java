package com.ssk.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	private WebDriver driver;

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	public CartPage(WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickCheckout() {
		checkoutButton.click();
	}

	//additional getter and setter for driver instance
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}

