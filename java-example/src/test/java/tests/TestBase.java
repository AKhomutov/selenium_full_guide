package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
   
   WebDriver wd;
   WebDriverWait wait;
   
   @Before
   public void start() {
      wd = new ChromeDriver();
      wait = new WebDriverWait(wd, 10);
   }
   
   @After
   public void stop() {
      wd.quit();
      wd = null;
   }
   
   void click(By by) {
      wd.findElement(by).click();
   }
   
   void loginAsAdmin() {
      String adminCred = "admin";
      wd.get("http://localhost/litecart/admin/");
      wd.findElement(By.name("username")).sendKeys(adminCred);
      wd.findElement(By.name("password")).sendKeys(adminCred);
      click(By.name("login"));
   }
}
