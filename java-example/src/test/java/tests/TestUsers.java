package tests;

import org.junit.Test;
import org.openqa.selenium.By;

public class TestUsers extends TestBase {
   
   @Test
   public void testCreateNewUserAndRelogin() {
      wd.get("http://localhost/litecart/");
      String email = "test" + randInt(0, 9999999) + "@email.com";
      String password = "Password1";
      createUser(email, password);
      logout();
      login(email, password);
      logout();
   }
   
   private void createUser(String email, String password) {
      click(By.cssSelector("#box-account-login a"));
      
      typeTextIn(By.name("firstname"), "John");
      typeTextIn(By.name("lastname"), "Doe" + randInt(0, 500));
      typeTextIn(By.name("address1"), "Yahar'gul, Unseen Village");
      typeTextIn(By.name("postcode"), "66666");
      typeTextIn(By.name("city"), "Yharnam");
      
      click(By.cssSelector(".select2-selection__arrow"));
      click(By.xpath("(//li[contains(.,'United States')])[1]"));
      
      typeTextIn(By.name("email"), email);
      typeTextIn(By.name("phone"), "+12345678909876");
      typeTextIn(By.name("password"), password);
      typeTextIn(By.name("confirmed_password"), password);
      
      click(By.name("create_account"));
   }
   
   private void logout() {
      By logoutLink = By.xpath("(//a[contains(.,'Logout')])[1]");
      defaultWait(logoutLink);
      click(logoutLink);
   }
   
   private void login(String email, String password) {
      typeTextIn(By.name("email"), email);
      typeTextIn(By.name("password"), password);
      click(By.name("login"));
   }
}
