package app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ProductPage;
import pages.ShoppingCartPage;

public class ApplicationManager {
   
   private WebDriver wd;
   
   private ShoppingCartPage shoppingCartPage;
   private HomePage homePage;
   private ProductPage productPage;
   
   public void init() {
      wd = new ChromeDriver();
      wd.manage().window().maximize();
      
      shoppingCartPage = new ShoppingCartPage(wd);
      homePage = new HomePage(wd);
      productPage = new ProductPage(wd);
   }
   
   public void stop() {
      wd.quit();
      wd = null;
   }
   
   public void addThreeProductsToShoppingCart() {
      for (int i = 0; i < 3; i++) {
         homePage.openHomePage();
         homePage.openFirstProductInTheList();
         String quantity = shoppingCartPage.getItemsInCartQuantity();
         productPage.selectSizeIfNeeded();
         productPage.clickAddToCart();
         shoppingCartPage.waitUntilItemsInCartQuantityIsIncreased(quantity, 1);
      }
   }
   
   public void openShoppingCart() {
      shoppingCartPage.openShoppingCart();
   }
   
   public void removeAllProductsFromTheShoppingCartOneByOne() {
      while (shoppingCartPage.orderSummaryIsPresent()) {
         shoppingCartPage.clickRemoveProduct();
         shoppingCartPage.orderSummaryIsAbsent();
      }
   }
}
