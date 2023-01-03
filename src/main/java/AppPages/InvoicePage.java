package AppPages;

import ElementMobileActions.MobileActions;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InvoicePage {
    static WebDriver driver;
    private final  By firstProductPrice = AppiumBy.xpath("//android.widget.TextView[@text='$29.99']");
    private final  By firstProductTax = AppiumBy.xpath("//android.widget.TextView[@text='Tax: $2.40']");
    private final  By firstProductTotal = AppiumBy.xpath("//android.widget.TextView[@text='Total: $32.39']");
    private final By finishBtnId = AppiumBy.accessibilityId("test-FINISH");

    public InvoicePage(WebDriver driver) {
        this.driver = driver;
    }
    //////////////////////// ASSERTIONS /////////////////////////////

    // Verifying That the Price Of Selected Item In Product's Page The Same Price When User Is CheckingOut
    public InvoicePage verifyThatInvoiceProductsHaveSamePricesAsSelected(){
        Assert.assertEquals(driver.findElement(firstProductPrice).getAttribute("text").toString()
                ,"$29.99");
        Assert.assertEquals(driver.findElement(firstProductTax).getAttribute("text").toString()
                ,"Tax: $2.40");
        Assert.assertEquals(driver.findElement(firstProductTotal).getAttribute("text").toString()
                ,"Total: $32.39");
        return this;
    }
    ////////////////////////// ACTIONS //////////////////////////
    @Step("Scroll Down To The Finish Button If Needed")
    public InvoicePage scrollDownToFinishBtn(){
        MobileActions.scrollDownToSpecificText("FINISH");
        return this;
    }
    @Step("Click On Finish Button To Complete The Purchasing Process")
    public AfterCompleteOrderPage clickOnFinishBtn(){
        driver.findElement(finishBtnId).click();
        return new AfterCompleteOrderPage(driver);
    }

}
