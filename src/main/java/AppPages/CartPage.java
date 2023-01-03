package AppPages;

import ElementMobileActions.MobileActions;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CartPage {
    static WebDriver driver;
    private final  By firstProductTitle = AppiumBy.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']");
    private final  By firstProductPrice = AppiumBy.xpath("//android.widget.TextView[@text='$29.99']");
    private final  By secondProductTitle = AppiumBy.xpath("//android.widget.TextView[@text='Sauce Labs Bike Light']");
    private final  By secondProductPrice = AppiumBy.xpath("//android.widget.TextView[@text='$9.99']");
    private final By firstProductRemoveBtn = AppiumBy.xpath("(//android.widget.TextView[@text='REMOVE'])[1]");
    private final By oneCartProduct = AppiumBy.xpath("(//android.widget.TextView[@text='1'])[1]");
    private final By zeroCartProduct = AppiumBy.xpath("//android.widget.ImageView[@bounds='[955,72][1055,172]']");
    private final By secondProductRemoveBtn = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-REMOVE']");
    private final By checkoutBtn = AppiumBy.accessibilityId("test-CHECKOUT");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    ////////////////////// ACTIONS /////////////////////////
    @Step("Get The Title Of The First Product In The Cart")
    public  String getFirstProductTitle(){
        return driver.findElement(firstProductTitle).getText();
    }
    @Step("Get The Price Of The First Product In The Cart")
    public  String getFirstProductPrice() {
        return driver.findElement(firstProductPrice).getText();
    }
    @Step("Get The Title Of The Second Product In The Cart")
    public  String getSecondProductTitle() {
        return driver.findElement(secondProductTitle).getText();
    }
    @Step("Get The Price Of The Second Product In The Cart")
    public  String getSecondProductPrice() {
        return driver.findElement(secondProductPrice).getText();
    }
    @Step("Remove The First Product From The Cart")
    public CartPage removeFirstProduct(){
        driver.findElement(firstProductRemoveBtn).click();
        return this;
    }
    @Step("Remove The Second Product From The Cart")
    public CartPage removeSecondProduct(){
        //MobileActions.scrollDownToSpecificText("REMOVE");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(secondProductRemoveBtn).click();
        return this;
    }
    @Step("Scroll Down To Checkout If Needed")
    public CartPage scrollDownToCheckoutBtn(){
        MobileActions.scrollDownToSpecificText("CHECKOUT");
        return this;
    }
    @Step("When User Has at Least On Item In The Cart Click On Checkout Button and Navigate To Checkout Page")
    public CheckoutPage navigateToCheckout(){
        driver.findElement(checkoutBtn).click();
        return new CheckoutPage(driver);
    }
    ///////////////////////// ASSERTIONS //////////////////////

    // Verify That Products Inside The Cart Have The Same Titles AS The Selected Products In The Products Page
    public CartPage verifyThatCartProductsHaveSameTitlesAsSelected(){
        Assert.assertEquals(getFirstProductTitle(),"Sauce Labs Backpack");
        Assert.assertEquals(getSecondProductTitle(),"Sauce Labs Bike Light");
        return this;
    }

    // Verify That Products Inside The Cart Have The Same Prices AS The Selected Products In The Products Page
    public CartPage verifyThatCartProductsHaveSamePricesAsSelected(){
        Assert.assertEquals(getFirstProductPrice(),"$29.99");
        Assert.assertEquals(getSecondProductPrice(),"$9.99");
        return this;
    }

    // Verify That After Clicking REMOVE Button Of The First Product The Product Is Deleted From Cart Successfully
    public CartPage verifyThatFirstItemIsRemoved(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(oneCartProduct).getText(),"1");
        return this;
    }
    // Verify That After Clicking REMOVE Button Of The Second Product The Cart Is Empty
    public CartPage verifyThatAllItemsIsRemoved(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(zeroCartProduct).getText(),"");
        return this;
    }
}
