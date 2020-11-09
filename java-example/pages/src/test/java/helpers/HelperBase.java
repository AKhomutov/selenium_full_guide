package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {
   protected WebDriver wd;
   protected WebDriverWait wait;
   
   public HelperBase(WebDriver wd) {
      this.wd = wd;
      wait = new WebDriverWait(wd, 10);
   }
   
   protected void selectDropdownOptionByIndex(By by, int index) {
      Select option = new Select(wd.findElement(by));
      option.selectByIndex(index);
   }
}
