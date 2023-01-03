package AppPages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AfterCompleteOrderPage {
    static WebDriver driver;
    private final static By orderConfirmedMsg = AppiumBy.xpath("//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']");

    public AfterCompleteOrderPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Get The Text Message The The Order Is Confirmed Successfully")
    public static String getOrderConfirmationMsg(){
        return driver.findElement(orderConfirmedMsg).getText();
    }
    //////////////////// ASSERTIONS ////////////////
    public void verifyOrderSavedSuccessfully(){
        Assert.assertEquals(getOrderConfirmationMsg(),"THANK YOU FOR YOU ORDER");
    }
}
