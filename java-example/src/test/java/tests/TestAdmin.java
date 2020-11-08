package tests;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class TestAdmin extends TestBase {
   
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
}
