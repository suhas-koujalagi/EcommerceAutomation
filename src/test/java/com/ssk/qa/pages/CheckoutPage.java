package com.ssk.qa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CheckoutPage {
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "first-name")
	private WebElement firstName;

	@FindBy(id = "last-name")
	private WebElement lastName;

	@FindBy(id = "postal-code")
	private WebElement postalCode;

	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(id = "finish")
	private WebElement finishButton;

	@FindBy(className = "complete-header")
	private WebElement successMessage;

	public CheckoutPage(WebDriver driver) {
		this.setDriver(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		PageFactory.initElements(driver, this);
	}

	public void enterUserDetails(String fname, String lname, String code) {
		typeSafely(firstName, fname, "First Name");
		typeSafely(lastName, lname, "Last Name");
		typeSafely(postalCode, code, "Postal Code");

		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
	}

	private void typeSafely(WebElement element, String value, String label) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(value);
		} catch (StaleElementReferenceException e) {
			// Retry once if the element got detached during first typing
			System.out.println(">>Retrying input for: " + label);
			WebElement refreshed = wait.until(ExpectedConditions.visibilityOf(element));
			refreshed.clear();
			refreshed.sendKeys(value);
		}
	}

	public void completeCheckout() {
		wait.until(ExpectedConditions.urlContains("checkout-step-two"));
		wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
	}

	public boolean isOrderSuccessful() {
		try {
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			return successMessage.isDisplayed();
		} catch (TimeoutException e) {
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
