package TestPackage;

import AppPages.CheckoutPage;
import AppPages.LogInPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JsonFileManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CheckoutTest {
    WebDriver driver;
    JsonFileManager login_data = new JsonFileManager("src/test/resources/testDataFiles/logInData.json");
    JsonFileManager checkout_data = new JsonFileManager("src/test/resources/testDataFiles/checkOutData.json");
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
                .clickOnLogInBtn()
                .addFirstProductToCart()
                .navigateToCart()
                .scrollDownToCheckoutBtn()
                .navigateToCheckout();
    }

    // verify that user can create a complete order
    @Test
    public void verifyUserCompleteOrderSuccessfully() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new CheckoutPage(driver)
                .enterFirstName(checkout_data.getTestData("firstName"))
                .enterLastName(checkout_data.getTestData("lastName"))
                .enterZipCode("zipCode")
                .scrollDownToContinueBtn()
                .clickOnContinueBtn()
                .verifyThatInvoiceProductsHaveSamePricesAsSelected()
                .scrollDownToFinishBtn()
                .clickOnFinishBtn()
                .verifyOrderSavedSuccessfully();
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    }
