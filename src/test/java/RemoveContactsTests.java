import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RemoveContactsTests extends AppiumConfig {

    @BeforeClass
    public void precondition(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("serg3@mail.com")
                .fillPassword("Ss34567$")
                .submitLogin();
    }

    @BeforeMethod
    public void providerContacts(){
        new ContactListScreen(driver).provideContacts();
    }



    @Test
    public void removeOneContactPositive(){
        Assert.assertTrue(
        new ContactListScreen(driver)
                .removeOneContact()
                .isContactRemoved()
        );
    }

    @Test
    public void removeAllContactsPositive(){
        Assert.assertTrue(
        new ContactListScreen(driver)
                .removeAllContacts()
                .isNoContactMessage()
        );
    }



}
