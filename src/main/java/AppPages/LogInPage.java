package AppPages;

import ElementMobileActions.MobileActions;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LogInPage {
    static WebDriver driver;
    private final By userNameId = AppiumBy.accessibilityId("test-Username");
    private final By passwordId = AppiumBy.accessibilityId("test-Password");
    private final By logInBtnId = AppiumBy.accessibilityId("test-LOGIN");
    private final static By errorMsg = AppiumBy.xpath("//android.widget.TextView[@text='Username and password do not match any user in this service.']");
    public LogInPage(WebDriver driver) {
        this.driver = driver;
        new MobileActions(driver);
    }
    /////////////////////// ACTIONS ////////////////////////////
    @Step("Enter Valid Username")
    public LogInPage enterUserName(String userName){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(userNameId).clear();
        driver.findElement(userNameId).sendKeys(userName);
        return this;
    }
    @Step("Enter Valid Password")
    public LogInPage enterPassword(String password){
        driver.findElement(passwordId).clear();
        driver.findElement(passwordId).sendKeys(password);
        return this;
    }
    @Step("Scroll Down To LogIn Button If Needed")
    public LogInPage scrollDownToLogInBtn(){
        MobileActions.scrollDownToSpecificText("LOGIN");
        return this;
    }
    @Step("Click On LogIn Button")
    public ProductsPage clickOnLogInBtn(){
        driver.findElement(logInBtnId).click();
        return new ProductsPage(driver);
    }
    @Step("Get The Text Of The Error Message When User Enter Wrong Username and Password")
    public static String getTextOfErrorMsg(){
        return driver.findElement(errorMsg).getText();
    }


}
