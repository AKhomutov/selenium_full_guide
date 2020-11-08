import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestAdmin {
   private WebDriver wd;
   private WebDriverWait wait;
   
   @Before
   public void start() {
      wd = new ChromeDriver();
      wait = new WebDriverWait(wd, 10);
   }
   
   @Test
   public void testLoginAsAdmin() {
      String adminCred = "admin";
      wd.get("http://localhost/litecart/admin/");
      wd.findElement(By.name("username")).sendKeys(adminCred);
      wd.findElement(By.name("password")).sendKeys(adminCred);
      wd.findElement(By.name("login")).click();
   }
   
   @After
   public void stop() {
      wd.quit();
      wd = null;
   }
}
