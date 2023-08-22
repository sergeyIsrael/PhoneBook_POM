import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {

    int i = new Random().nextInt(1000) + 1000;

    @Test
    public void registrationPositive(){
        Assert.assertTrue(
//        Запускаем первый экран SplashScreen
                new SplashScreen(driver)
//                Переходим на следующий экран AuthenticationScreen
                        .gotoAuthenticationScreen()
                        .fillEmail("reg_" + i + "@mail.com")
                        .fillPassword("Ss34567$")
                        .submitRegistration()
                        .isContactListActivityPresent()
        );
    }

    @Test
    public void registrationNegativeWrongEmail(){
        Assert.assertTrue(
//        Запускаем первый экран SplashScreen
                new SplashScreen(driver)
//                Переходим на следующий экран AuthenticationScreen
                        .gotoAuthenticationScreen()
                        .fillEmail("reg_" + i + "mail.com")
                        .fillPassword("Ss34567$")
                        .submitRegistrationNegative()
                        .isErrorMessageHasText("{username=must be a well-formed email address}")
        );
    }

}
