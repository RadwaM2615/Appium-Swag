package AppPages;

import ElementMobileActions.MobileActions;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    static WebDriver driver;
    private final By firstNameId = AppiumBy.accessibilityId("test-First Name");
    private final By lastNameId = AppiumBy.accessibilityId("test-Last Name");
    private final By zipCodeId = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private final By continueBtn = AppiumBy.accessibilityId("test-CONTINUE");
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("After Navigation To Checkout Page Enter User's First Name")
    public CheckoutPage enterFirstName(String firstName){
        driver.findElement(firstNameId).clear();
        driver.findElement(firstNameId).sendKeys(firstName);
        return this;
    }
    @Step("After Navigation To Checkout Page Enter User's Second Name")
    public CheckoutPage enterLastName(String lastName){
        driver.findElement(lastNameId).clear();
        driver.findElement(lastNameId).sendKeys(lastName);
        return this;
    }
    @Step("After Navigation To Checkout Page Enter User's Zip Code")
    public CheckoutPage enterZipCode(String zipCode){
        driver.findElement(zipCodeId).clear();
        driver.findElement(zipCodeId).sendKeys(zipCode);
        return this;
    }
    @Step("Scroll Down to Continue Button If Needed")
    public CheckoutPage scrollDownToContinueBtn(){
        MobileActions.scrollDownToSpecificText("CONTINUE");
        return this;
    }
    @Step("Click On Continue Button")
    public InvoicePage clickOnContinueBtn(){
        driver.findElement(continueBtn).click();
        return new InvoicePage(driver);
    }
}
