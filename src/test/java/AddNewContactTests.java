import config.AppiumConfig;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.BaseScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTests extends AppiumConfig {

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
    public void addNewContactPositive(){

         Contact contact = Contact.builder()
                 .name("Dima_" + i)
                 .lastName("Samoilov")
                 .email("sss_" + i + "@mail.com")
                 .phone("12345678" + i)
                 .address("Moscow")
                 .description("who is it?")
                 .build();

         Assert.assertTrue(
         new ContactListScreen(driver)
                 .openContactForm()
                 .fillContactForm(contact)
                 .submitContact()
//                 .isContactAdded(contact)
                 .isContactAddedScroll(contact)
         );
     }



}
