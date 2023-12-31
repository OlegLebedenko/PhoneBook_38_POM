package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;

public class AddNewContactScreen extends BaseScreen{

    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {

        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputName']")
    MobileElement inputName;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputLastName']")
    MobileElement inputLastName;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement inputEmail;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPhone']")
    MobileElement inputPhone;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputAddress']")
    MobileElement inputAddress;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputDesc']")
    MobileElement inputDesc;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/createBtn']")
    MobileElement createBtn;

    public AddNewContactScreen fillContactForm(Contact contact){
        waitElement(createBtn, 5);
        type(inputName, contact.getName());
        type(inputLastName, contact.getLastName());
        type(inputEmail, contact.getEmail());
        type(inputPhone, contact.getPhone());
        type(inputAddress, contact.getAddress());
        type(inputDesc, contact.getDescription());
        return this;
    }
    public ContactListScreen submitContact(){
        createBtn.click();
        return new ContactListScreen(driver);
    }
}
