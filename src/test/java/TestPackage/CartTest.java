package TestPackage;

import AppPages.LogInPage;
import AppPages.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import utils.JsonFileManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CartTest {
    WebDriver driver;
    JsonFileManager login_data = new JsonFileManager("src/test/resources/testDataFiles/logInData.json");
    @BeforeClass
    public void setupDevice() throws MalformedURLException {
        String AppName = System.getProperty("user.dir") + "\\src\\test\\resources\\testDataFiles\\Android.SauceLabs.Mobile.Sample.app.2.2.0.apk";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Pixel 3a XL API 30");
        caps.setCapability("appium:app", AppName);
        caps.setCapability("appWaitActivity","com.swaglabsmobileapp.MainActivity");
        caps.setCapability("appium:platformVersion", "11");
        caps.setCapability("appium:automationName", "UiAutomator2");
        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
        new LogInPage(driver)
                .enterUserName(login_data.getTestData("userName"))
                .enterPassword(login_data.getTestData("password"))
                .clickOnLogInBtn();
    }
    // Verifying that the products in the cart have the same titles and prices as the selected
    @Test
    public void verifySelectedProductsAddedToCartSuccessfully() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new ProductsPage(driver)
                .addFirstProductToCart()
                .addSecondProductToCart()
                .navigateToCart()
                .verifyThatCartProductsHaveSameTitlesAsSelected()
                .verifyThatCartProductsHaveSamePricesAsSelected();
    }

    /* Verifying When remove one item form cart the cart product counter is decreasing
       and when remove the second the cart is empty*/
    @Test
    public void verifyWhenDeleteAllProductsCartIsEmpty() {
        new ProductsPage(driver)
                .addFirstProductToCart()
                .addSecondProductToCart()
                .navigateToCart()
                .removeFirstProduct()
                .verifyThatFirstItemIsRemoved()
                .removeSecondProduct()
                .verifyThatAllItemsIsRemoved();
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
