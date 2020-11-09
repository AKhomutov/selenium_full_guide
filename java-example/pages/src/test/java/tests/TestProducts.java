package tests;

import org.junit.Test;

public class TestProducts extends TestBase {
   
   @Test
   public void testAddProductsToShoppingCartAndRemoveThem() {
      app.addThreeProductsToShoppingCart();
      app.openShoppingCart();
      app.removeAllProductsFromTheShoppingCartOneByOne();
   }
}
