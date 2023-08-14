import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {

    int i = new Random().nextInt(1000) + 1000;

    @Test
    public void registrationPositive(){
        Assert.assertTrue(
                new SplashScreen(driver)
                        .gotoAuthenticationScreen()
                        .fillEmail("dada_" + i + "@mail.com")
                        .fillPassword("Km12356#")
                        .submitRegistration()
                        .isContactListActivityPresent()
        );
    }

    @Test
    public void RegistrationNegativeWrongEmail(){
        Assert.assertTrue(
                new SplashScreen(driver)
                        .gotoAuthenticationScreen()
                        .fillEmail("dada_" + i + "mail.com")
                        .fillPassword("Km12356#")
                        .submitRegistrationNegative()
                        .isErrorMessageHasText("{username=must be a well-formed email address}")
        );
    }
}
