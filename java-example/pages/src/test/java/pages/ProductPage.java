package pages;

import helpers.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends HelperBase {
   public ProductPage(WebDriver wd) {
      super(wd);
      PageFactory.initElements(wd, this);
   }
   
   @FindBy (name = "add_cart_product")
   private WebElement buttonAddToCart;
   
   public ProductPage clickAddToCart() {
      buttonAddToCart.click();
      return this;
   }
   
   public void selectSizeIfNeeded() {
      By dropdownSize = By.name("options[Size]");
      if (wd.findElements(dropdownSize).size() == 1) {
         selectDropdownOptionByIndex(dropdownSize, 1);
      }
   }
}
