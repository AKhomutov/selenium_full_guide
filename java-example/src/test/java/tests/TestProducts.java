package tests;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class TestProducts extends TestBase {
   
   @Test
   public void testStickersPresence() {
      wd.get("http://localhost/litecart/");
      int ducks = wd.findElements(By.cssSelector(".product")).size();
      for (int i = 0; i < ducks; i++){
         assertEquals(
           wd.findElements(By.cssSelector(".product")).get(i).findElements(By.cssSelector(".sticker")).size(),1);
      }
   }
}
