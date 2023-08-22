package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

// Самый первый экран заставка (SplashScreen)
public class SplashScreen extends BaseScreen {

    public SplashScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

//    Найди элемент по xpath и сохрани переменную versionTextView
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/version_text']")
    MobileElement versionTextView;

    public String getCurrentVersion(){
        return versionTextView.getText();
    }

//    Переход на следующий экран AuthenticationScreen
    public AuthenticationScreen gotoAuthenticationScreen(){
        return new AuthenticationScreen(driver);
    }



}
