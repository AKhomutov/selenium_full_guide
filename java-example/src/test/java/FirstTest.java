import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTest {
   
   private WebDriver wd;
   private WebDriverWait wait;
   
   @Before
   public void start() {
      wd = new ChromeDriver();
      wait = new WebDriverWait(wd, 10);
   }
   
   @Test
   public void firstTest() {
      wd.get("https://www.google.com/");
      wd.findElement(By.name("q")).sendKeys("hello");
      wd.findElement(By.name("btnK")).click();
      wait.until(titleIs("hello - Пошук Google"));
   }
   
   @After
   public void stop() {
      wd.quit();
      wd = null;
   }
}
