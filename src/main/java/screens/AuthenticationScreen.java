package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationScreen extends BaseScreen{

    public AuthenticationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement inputEmail;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPassword']")
    MobileElement inputPassword;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/regBtn']")
    MobileElement buttonRegBtn;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/loginBtn']")
    MobileElement buttonLogBtn;
    @FindBy(id = "android:id/message")
    MobileElement errorTextView;
    @FindBy(id = "android:id/button1")
    MobileElement okBtn;

    public AuthenticationScreen fillEmail(String email) {
        waitElement(inputEmail, 5);
        type(inputEmail, email);
        return this;
    }
    public AuthenticationScreen fillPassword(String password) {
        type(inputPassword, password);
        return this;
    }

    public ContactListScreen submitLogin(){

        buttonLogBtn.click();
        return new ContactListScreen(driver);
    }
    public ContactListScreen submitRegistration(){

        buttonRegBtn.click();
        return new ContactListScreen(driver);
    }
    public AuthenticationScreen submitRegistrationNegative(){

        buttonRegBtn.click();
        return this;
    }
    public boolean isErrorMessageHasText(String text){
//        return errorTextView.getText().contains(text);
        return isErrorMessageContainsText(text);
    }
}
