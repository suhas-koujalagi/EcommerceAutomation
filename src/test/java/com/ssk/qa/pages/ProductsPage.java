package com.ssk.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	private WebDriver driver;

	@FindBy(xpath = "(//button[contains(text(),'Add to cart')])[1]")
	private WebElement addToCartButton;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	private WebElement cartIcon;

	@FindBy(className = "shopping_cart_badge")
	private WebElement cartBadge;

	@FindBy(xpath = "//span[@class='title']")
	private WebElement productPageTitle;

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void addProductToCart() {
		addToCartButton.click();
	}

	public void openCart() {
		cartIcon.click();
	}

	public boolean isProductAdded() {
		return cartBadge.isDisplayed();
	}

	// to verify weather we are on product page
	public boolean isOnProductPage() {
		return productPageTitle.isDisplayed();
	}

}

