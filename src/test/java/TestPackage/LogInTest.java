package TestPackage;

import AppPages.LogInPage;
import AppPages.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.JsonFileManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LogInTest {
    WebDriver driver;
    JsonFileManager login_data = new JsonFileManager("src/test/resources/testDataFiles/logInData.json");
    @BeforeMethod
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
    }
    /*
       Verifying LogIn Button Functionality Using In the First Testcase Valid Credentials
       and Invalid Credentials In The Second Testcase.
     */
    @Test
    public void verifyThatUserCanLogInWithValidCredential() {
        new LogInPage(driver)
                .enterUserName(login_data.getTestData("userName"))
                .enterPassword(login_data.getTestData("password"))
                .scrollDownToLogInBtn()
                .clickOnLogInBtn();
        Assert.assertEquals(ProductsPage.getProductsPageTitle(),"PRODUCTS");
    }
    @Test
    public void verifyThatUserCanNotLogInWithValidCredential() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        new LogInPage(driver)
                .enterUserName(login_data.getTestData("invalidUserName"))
                .enterPassword(login_data.getTestData("invalidPassword"))
                .scrollDownToLogInBtn()
                .clickOnLogInBtn();
        Assert.assertEquals(LogInPage.getTextOfErrorMsg(),"Username and password do not match any user in this service.");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
