package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactListScreen extends BaseScreen{

    public ContactListScreen(AppiumDriver<MobileElement> driver) {

        super(driver);
    }
    String phoneNumber;

    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOptions;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logoutBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addContactBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contacts;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> names;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> phones;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement yesBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emptyTxt']")
    MobileElement emptyTxt;


public boolean isContactListActivityPresent(){

    return shouldHave(activityTextView, "Contact list", 5);
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
    public boolean checkContainsText(List<MobileElement> list, String text){
        for (MobileElement element : list){
            if (element.getText().contains(text)) return true;
        }
        return false;
    }
    public boolean isContactAdded(Contact contact){

        boolean checkName = checkContainsText(names, contact.getName() + " " + contact.getLastName());
        boolean checkPhone = checkContainsText(phones, contact.getPhone());

        return checkName && checkPhone;
    }
    public ContactListScreen removeOneContact(){
        waitElement(addContactBtn, 5);
        MobileElement contact = contacts.get(0);
        phoneNumber = phones.get(0).getText();
        Rectangle rect = contact.getRect();

        int xStart = rect.getX() + rect.getWidth() / 8;
        int xEnd = xStart + rect.getWidth() * 6 / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd, y))
                .release()
                .perform();

        yesBtn.click();
        pause(3000);
        return this;
    }
    public boolean isContactRemoved(){
        boolean res = phones.contains(phoneNumber);
        return !res;
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
        while(contacts.size() < 3){
            addNewContact();
        }
        return this;
    }
    public ContactListScreen addNewContact(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("Karl_" + i)
                .lastName("Stuart")
                .phone("08237654" + i)
                .email("karl_" + i + "@gmail.com")
                .address("Holon")
                .description("Good dude")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContact();
        return this;
    }
    public EditContactScreen editOneContact(){
        waitElement(addContactBtn, 5);
        MobileElement contact = contacts.get(0);
        Rectangle rect = contact.getRect();

        int xStart = rect.getX() + rect.getWidth() / 8;
        int xEnd = xStart + rect.getWidth() * 6 / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xEnd, y))
                .moveTo(PointOption.point(xStart, y))
                .release()
                .perform();

        pause(3000);
        return new EditContactScreen(driver);
    }
    public boolean isContactContains(String text){
        contacts.get(0).click();
        Contact contact = new ViewContactScreen(driver)
                .viewContactObject();
        driver.navigate().back();
        return contact.toString().contains(text);
    }

    public ContactListScreen scrollContactList(){

        waitElement(addContactBtn, 5);
        MobileElement contact = contacts.get(6);
        Rectangle rect = contact.getRect();

        int x = rect.getX() + rect.getWidth() / 2;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(x, y))
                .moveTo(ElementOption.element(contacts.get(0)))
                .release()
                .perform();

        pause(3000);
        return this;
    }
    public ContactListScreen scrolList(){
        waitElement(addContactBtn, 5);
        MobileElement contact = contacts.get(contacts.size() - 1);
        Rectangle rect = contact.getRect();

        int x = rect.getX() + rect.getWidth() / 2;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(x, y))
                .moveTo(PointOption.point(x, 0))
                .release()
                .perform();

          return this;
    }
    public boolean isEndOfList(){
    String beforeScroll = names.get(names.size() - 1).getText() + " "
            + phones.get(phones.size() - 1).getText();
    scrolList();
    String afterScroll = names.get(names.size() - 1).getText() + " "
            + phones.get(phones.size() - 1).getText();
    if(beforeScroll.equals(afterScroll)) return true;
    return false;
    }
    public boolean isContactAddedScroll(Contact contact){
        boolean res = false;
        while (!res) {
            boolean checkName = checkContainsText(names, contact.getName() + " " + contact.getLastName());
            boolean checkPhone = checkContainsText(phones, contact.getPhone());
            res = checkName && checkPhone;
            if(res == false) isEndOfList();
        }
        return res;
    }
}
