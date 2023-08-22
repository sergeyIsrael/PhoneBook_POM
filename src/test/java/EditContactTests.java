import config.AppiumConfig;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class EditContactTests extends AppiumConfig {

    int i = (int) (System.currentTimeMillis() / 1000) % 360;

    @BeforeMethod
    public void precondition(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("sergei1@mail.com")
                .fillPassword("Ss34567$")
                .submitLogin();
    }

    @Test
    public  void editOneContactPositive(){
        String text = "updated_" + i + "@mail.com";
        new ContactListScreen(driver)
                .editOneContactSwipe()
                .editEmail(text)
                .submitEditContact()
                .isContactContains(text);
    }


}
