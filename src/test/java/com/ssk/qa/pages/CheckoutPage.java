package com.ssk.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    private WebDriver driver;

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
    private WebElement successMessage ;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserDetails(String fname, String lname, String code) {
        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        postalCode.sendKeys(code);
    }

    public void completeCheckout() {
        continueButton.click();
        finishButton.click();
    }

    public boolean isOrderSuccessful() {
        return successMessage.isDisplayed();
    }
}

