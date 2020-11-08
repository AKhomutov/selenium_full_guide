package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class TestBase {
   
   WebDriver wd;
   WebDriverWait wait;
   
   @Before
   public void start() {
      wd = new ChromeDriver();
      //wd = new FirefoxDriver();
      //wd = new InternetExplorerDriver();
      wait = new WebDriverWait(wd, 10);
      wd.manage().window().maximize();
   }
   
   @After
   public void stop() {
      wd.quit();
      wd = null;
   }
   
   void defaultWait(By by) {
      wait.until(ExpectedConditions.presenceOfElementLocated(by));
      wait.until(ExpectedConditions.visibilityOfElementLocated(by));
      wait.until(ExpectedConditions.elementToBeClickable(by));
   }
   
   void click(By by) {
      wd.findElement(by).click();
   }
   
   protected void typeTextIn(By by, String text) {
      wd.findElement(by).clear();
      wd.findElement(by).sendKeys(text);
   }
   
   void loginAsAdmin() {
      String adminCred = "admin";
      wd.get("http://localhost/litecart/admin/");
      wd.findElement(By.name("username")).sendKeys(adminCred);
      wd.findElement(By.name("password")).sendKeys(adminCred);
      click(By.name("login"));
   }
   
   void clickOnLeftSectionMenuWithName(String name) {
      By section = By.xpath("//*[@class=\"name\" and contains(text(), '" + name + "')]//ancestor::li");
      click(section);
      new WebDriverWait(wd, 20).until(ExpectedConditions.attributeToBe(section, "class", "selected"));
      new WebDriverWait(wd, 20).until(ExpectedConditions.titleIs(name + " | My Store"));
   }
   
   int randInt(int min, int max) {
      Random rand = new Random();
      return rand.nextInt((max - min) + 1) + min;
   }
}
