package AppPages;


import ElementMobileActions.MobileActions;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    static WebDriver driver;
    private final static By productsPageTitle = AppiumBy.xpath("//android.widget.TextView[@text='PRODUCTS']");
    private final By firstProduct = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='test-ADD TO CART'])[1]");
    private final By secondVisibleProduct = AppiumBy.accessibilityId("test-ADD TO CART");
    private final By cartIcon = AppiumBy.xpath("//android.widget.ImageView[@bounds='[955,72][1055,172]']");
    private final static By totalNumberOfCartProducts = AppiumBy.xpath("//android.widget.TextView[@text='2']");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        new MobileActions(driver);
    }
    @Step("Get The Product's Page Title (PRODUCTS)")
    public static String getProductsPageTitle(){
        return driver.findElement(productsPageTitle).getText();
    }
    @Step("Add The First Product To The Cart")
    public ProductsPage addFirstProductToCart(){
        driver.findElement(firstProduct).click();
        return this;
    }
    @Step("Scroll Down To The Second Product If Needed")
    public ProductsPage scrollDownToSecondProduct(){
        //((JavascriptExecutor) driver) .executeScript("window.scrollTo(0,1000)");
        MobileActions.scrollDownToSpecificText("Sauce Labs Onesie");
        return this;
    }
    @Step("Add The Second Product To The Cart")
    public ProductsPage addSecondProductToCart(){
        driver.findElement(secondVisibleProduct).click();
        return this;
    }
    @Step("Get The Total Number Of Products In The Cart")
    public static String getNumberOfProductsInCart() {
        return driver.findElement(totalNumberOfCartProducts).getText();
    }
    @Step("Navigate To The Cart To Check The Selected Products")
    public CartPage navigateToCart(){
        driver.findElement(cartIcon).click();
        return new CartPage(driver);
    }
}
