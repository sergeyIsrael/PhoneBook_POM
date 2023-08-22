package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactListScreen extends BaseScreen {

    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    String phoneNumber;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityViewText;

    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOptions;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logoutBtn;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addContactBtn;

    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement yesBtn;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contacts;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> names;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> phones;


    public boolean isContactListActivityPresent() {
        return shouldHave(activityViewText, "Contact list", 5);
    }

    public AuthenticationScreen logout(){
        moreOptions.click();
        logoutBtn.click();
        return new AuthenticationScreen(driver);
    }

    public AddNewContactScreen openContactForm(){
        waitElement(addContactBtn, 5);
        addContactBtn.click();
        return new AddNewContactScreen(driver);
    }

    public boolean isContactAdded(Contact contact){
        boolean checkName = checkContainsText( names, contact.getName() + " " + contact.getLastName() );
        boolean checkPhone = checkContainsText( phones, contact.getPhone() );
        return checkName && checkPhone;
    }

    public boolean checkContainsText(List<MobileElement> list, String text){
        for(MobileElement element : list){
            if(element.getText().contains(text))
                return true;
        }
        return false;
    }

    public ContactListScreen removeOneontact(){
        waitElement(addContactBtn, 5);
        MobileElement contact = contacts.get(0);
        phoneNumber = phones.get(0).getText();
        Rectangle rect = contact.getRect();
        int xStart = rect.getX() + rect.getWidth() / 8;
        int xEnd = xStart+ rect.getWidth() * 6 / 8; // 1/8 + 6/8 = 7/8
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart, y)) // Нажать и держать tut
                .moveTo(PointOption.point(xEnd, y)) // тянуть до
                .release() // Отпустить
                .perform();

        yesBtn.click();
        pause(2000);
        return this;
    }

    public boolean isContactRemoved(){
        boolean res = phones.contains(phoneNumber); // false
        return !res; // true
    }



}
