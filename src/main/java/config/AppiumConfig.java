package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConfig {

    public static AppiumDriver<MobileElement> driver;

    @BeforeSuite
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "p4a9");
//        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        capabilities.setCapability("appPackage", "com.sheygam.contactapp");
        capabilities.setCapability("appActivity", ".SplashActivity");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/MacbookPro/Tools/contacts-android.apk");

        driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }


}
