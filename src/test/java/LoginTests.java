import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

   @Test
    public void loginPositive(){
       Assert.assertTrue(
       new SplashScreen(driver)
               .gotoAuthenticationScreen()
               .fillEmail("dara@mail.com")
               .fillPassword("Km12356#")
               .submitLogin()
               .isContactListActivityPresent()
       );
   }

   @AfterMethod
    public void postcondition(){
       if(new ContactListScreen(driver).isContactListActivityPresent()){
           new ContactListScreen(driver).logout();
           new SplashScreen(driver);
       }
   }


}
