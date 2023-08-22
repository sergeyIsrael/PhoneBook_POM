import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RemoveContactsTests extends AppiumConfig {

    @BeforeClass
    public void precondition(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("sergei1@mail.com")
                .fillPassword("Ss34567$")
                .submitLogin();
    }

    @Test
    public void removeOneContactPositive(){
        Assert.assertTrue(
        new ContactListScreen(driver)
                .removeOneontact()
                .isContactRemoved()
        );

    }



}
