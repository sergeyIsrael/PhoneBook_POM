import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginPositive(){
        Assert.assertTrue(
//        Запускаем первый экран SplashScreen
        new SplashScreen(driver)
//                Переходим на следующий экран AuthenticationScreen
                .gotoAuthenticationScreen()
                .fillEmail("sergei1@mail.com")
                .fillPassword("Ss34567$")
                .submitLogin()
                .isContactListActivityPresent()
        );
    }

    @AfterMethod
    public void postCondition(){
        if (new ContactListScreen(driver).isContactListActivityPresent()){
            new ContactListScreen(driver).logout();
            new SplashScreen(driver);
        }
    }



}
