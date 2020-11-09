package pages;

import helpers.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends HelperBase {
   public HomePage(WebDriver wd) {
      super(wd);
      PageFactory.initElements(wd, this);
   }
   
   @FindBy(css = "li.product")
   private WebElement products;
   
   public void openHomePage() {
      wd.get("http://localhost/litecart/");
   }
   
   private List<WebElement> getListOfAllProducts() {
      return wd.findElements(By.cssSelector("li.product"));
   }
   
   public void openFirstProductInTheList() {
      List<WebElement> ducks = getListOfAllProducts();
      WebElement firstDuck = ducks.get(0);
      firstDuck.click();
   }
}
