package TestPackage;

import AppPages.LogInPage;
import AppPages.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JsonFileManager;

import java.net.MalformedURLException;
import java.net.URL;

public class ProductsTest {
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
    /*
      Verifying that user can add Products To The Cart Successfully
     */
    @Test
    public void verifySelectedProductsAddedToCartSuccessfully() {

        new ProductsPage(driver)
                .addFirstProductToCart()
                .addSecondProductToCart();
        Assert.assertEquals(ProductsPage.getNumberOfProductsInCart(),"2");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
