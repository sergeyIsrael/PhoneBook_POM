package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.lang3.text.StrTokenizer;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseScreen {

    AppiumDriver<MobileElement> driver;

    public BaseScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
//        Предоставляет доступ к Коллекции элементов на this странице
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void type(MobileElement element, String text){
        element.click();
        element.clear();
        element.sendKeys(text);
        driver.hideKeyboard();
    }

    public void pause(int time) {
       try {
           Thread.sleep(time);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public void waitElement(MobileElement element, int time) {
        new WebDriverWait(driver, time)
//               Жди time пока element не станет видимым
                .until(ExpectedConditions.visibilityOf(element));
    }

    public boolean shouldHave(MobileElement element, String text, int time) {
        return new WebDriverWait(driver, time)
                .until(ExpectedConditions.textToBePresentInElement( element, text));
    }


    public boolean isErrorMessageContainsText(String text){
        Alert alert = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
       boolean res = alert.getText().contains(text);
        alert.accept();
        return res;
    }



}
