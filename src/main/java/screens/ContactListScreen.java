package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emptyTxt']")
    MobileElement emptyTxt;

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

    public ContactListScreen removeOneContact(){
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

    public ContactListScreen removeAllContacts(){
        waitElement(addContactBtn, 5);
        while (contacts.size() > 0){
            removeOneContact();
        }
        return this;
    }

    public boolean isNoContactMessage(){
        return shouldHave(emptyTxt, "No Contacts. Add One more!", 5);
    }

    public ContactListScreen provideContacts(){
        while (contacts.size() < 2){
            addNewContact();
        }
        return this;
    }

    public ContactListScreen addNewContact(){
        int i = (int) (System.currentTimeMillis() / 1000) % 360;
        Contact contact = Contact.builder()
                .name("Dima_" + i)
                .lastName("Samoilov")
                .email("sss_" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Moscow")
                .description("who is it?")
                .build();

                new ContactListScreen(driver)
                        .openContactForm()
                        .fillContactForm(contact)
                        .submitContact();
        return this;
    }

    public EditContactScreen editOneContactSwipe(){
        waitElement(addContactBtn, 5);
        MobileElement contact = contacts.get(0);
        phoneNumber = phones.get(0).getText();
        Rectangle rect = contact.getRect();
        int xStart = rect.getX() + rect.getWidth() / 8;
        int xEnd = xStart+ rect.getWidth() * 6 / 8; // 1/8 + 6/8 = 7/8
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xEnd, y)) // Нажать и держать tut
                .moveTo(PointOption.point(xStart, y)) // тянуть до
                .release() // Отпустить
                .perform();

        pause(2000);
        return new EditContactScreen(driver);
    }

    public boolean isContactContains(String text){
        contacts.get(0).click();
        Contact contact = new ViewContactScreen(driver)
                .viewContactObject();

        driver.navigate().back();
        return contact.toString().contains(text);
    }

    public ContactListScreen scrollList(){
        waitElement(addContactBtn,5);
        MobileElement contact = contacts.get(contacts.size() - 1);
        phoneNumber = phones.get(0).getText();
        Rectangle rect = contact.getRect();
        int x = rect.getX() + rect.getWidth() / 2;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(x, 1000)) // Нажать и держать tut
//        touchAction.longPress(PointOption.point(x, y)) // Нажать и держать tut
                .moveTo(PointOption.point(x, 0)) // тянуть до
                .release() // Отпустить
                .perform();
        return this;
    }

    public boolean isEndOfList(){
        String beforeScroll = names.get(names.size() -1).getText() +
                " " + phones.get(phones.size() -1).getText();
        scrollList();
        String afterScroll = names.get(names.size() -1).getText() +
                " " + phones.get(phones.size() -1).getText();
        if(beforeScroll.equals(afterScroll))
            return true;
        return false;
    }

    public boolean isContactAddedScroll(Contact contact){
        boolean res = false;
        while (!res) {
        boolean checkName = checkContainsText( names, contact.getName() + " " + contact.getLastName() );
        boolean checkPhone = checkContainsText( phones, contact.getPhone() );
        res = checkName && checkPhone;
        if (res == false) isEndOfList();
        }
        return res;
    }




}
