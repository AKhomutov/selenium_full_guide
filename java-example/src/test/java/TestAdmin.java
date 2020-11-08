import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class TestAdmin {
   private WebDriver wd;
   private WebDriverWait wait;
   
   @Before
   public void start() {
      wd = new ChromeDriver();
      wait = new WebDriverWait(wd, 10);
   }
   
   private void click(By by) {
      wd.findElement(by).click();
   }
   
   private void loginAsAdmin() {
      String adminCred = "admin";
      wd.get("http://localhost/litecart/admin/");
      wd.findElement(By.name("username")).sendKeys(adminCred);
      wd.findElement(By.name("password")).sendKeys(adminCred);
      click(By.name("login"));
   }
   
   @Test
   public void testLoginAsAdmin() {
      loginAsAdmin();
   }
   
   @Test
   public void testOpenEverySection() {
      loginAsAdmin();
      int menu = wd.findElements(By.id("app-")).size();
      for ( int i = 0; i < menu; i++){
         wd.findElements(By.id("app-")).get(i).click();
         int submenu = wd.findElements(By.cssSelector("#app- ul a")).size();
         for (int l = 0; l < submenu; l++){
            wd.findElements(By.cssSelector("#app- ul a")).get(l).click();
            assertTrue(wd.findElement(By.cssSelector("h1")).isDisplayed());
         }
      }
   }
   
   @After
   public void stop() {
      wd.quit();
      wd = null;
   }
}
