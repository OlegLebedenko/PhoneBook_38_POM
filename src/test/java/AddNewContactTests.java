import config.AppiumConfig;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTests extends AppiumConfig {

    int i = (int) (System.currentTimeMillis() / 1000) % 3600;

    @BeforeMethod
    public void precondition(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("dara@mail.com")
                .fillPassword("Km12356#")
                .submitLogin();
    }

    @Test
    public void addNewContactPositive(){
        Contact contact = Contact.builder()
                .name("Karl_" + i)
                .lastName("Stuart")
                .phone("08237654" + i)
                .email("karl_" + i + "@gmail.com")
                .address("Holon")
                .description("Good dude")
                .build();

        Assert.assertTrue(
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContact()
             //   .isContactAdded(contact)
                .isContactAddedScroll(contact)
        );

    }

}
