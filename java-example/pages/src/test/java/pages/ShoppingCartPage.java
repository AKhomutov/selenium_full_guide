package pages;

import helpers.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class ShoppingCartPage extends HelperBase {
   public ShoppingCartPage(WebDriver wd) {
      super(wd);
      PageFactory.initElements(wd, this);
   }
   
   @FindBy(css = "span.quantity")
   private WebElement labelItemsInCartQuantity;
   
   @FindBy(xpath = "//a[contains(.,'Checkout »')]")
   private WebElement linkCheckout;
   
   @FindBy(name = "remove_cart_item")
   private WebElement buttonRemove;
   
   public String getItemsInCartQuantity() {
      return labelItemsInCartQuantity.getText();
   }
   
   public void waitUntilItemsInCartQuantityIsIncreased(String quantity, int amount) {
      wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), Integer.parseInt(quantity) + amount + ""));
   }
   
   public void openShoppingCart() {
      linkCheckout.click();
   }
   
   public void clickRemoveProduct() {
      buttonRemove.click();
   }
   
   public boolean orderSummaryIsPresent() {
      return wd.findElements(By.xpath("//*[@id='box-checkout-summary']")).size() != 0;
   }
   
   public void orderSummaryIsAbsent() {
      wait.until(stalenessOf(wd.findElement(By.xpath("//*[@id='box-checkout-summary']"))));
   }
}
