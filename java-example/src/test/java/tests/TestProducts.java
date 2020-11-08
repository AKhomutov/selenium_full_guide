package tests;

import javafx.scene.paint.Color;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

import static javafx.scene.paint.Color.GREY;
import static javafx.scene.paint.Color.RED;
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
   
   @Test
   public void testProductProperties() {
      By mainPageProductLink = By.cssSelector("div#box-campaigns a.link");
      By mainPageRegularPrice = By.cssSelector("div#box-campaigns s");
      By mainPageCampaignPrice = By.cssSelector("div#box-campaigns strong");
      By productPageProductName = By.cssSelector("h1.title");
      By productPageRegularPrice = By.cssSelector("div.price-wrapper s");
      By productPageCampaignPrice = By.cssSelector("div.price-wrapper strong");
      
      wd.get("http://localhost/litecart/");
      String name = wd.findElement(By.cssSelector("div#box-campaigns div.name")).getText();
      String campaignPrice = wd.findElement(mainPageCampaignPrice).getText();
      String regularPrice = wd.findElement(mainPageRegularPrice).getText();
      assertTextIsBold(mainPageCampaignPrice);
      assertElementColorIs(getColorFrom(mainPageCampaignPrice), RED);
      assertTextIsCrossedOut(mainPageRegularPrice);
      assertElementColorIs(getColorFrom(mainPageRegularPrice), GREY);
      assert getFontSize(mainPageRegularPrice) < getFontSize(mainPageCampaignPrice);
      
      wd.findElement(mainPageProductLink).click();
      assertEquals(name, wd.findElement(productPageProductName).getText());
      assertEquals(campaignPrice, wd.findElement(productPageCampaignPrice).getText());
      assertEquals(regularPrice, wd.findElement(productPageRegularPrice).getText());
      assertTextIsBold(productPageCampaignPrice);
      assertElementColorIs(getColorFrom(productPageCampaignPrice), RED);
      assertTextIsCrossedOut(productPageRegularPrice);
      assertElementColorIs(getColorFrom(productPageRegularPrice), GREY);
      assert getFontSize(productPageRegularPrice) < getFontSize(productPageCampaignPrice);
   }
   
   @Test
   public void testCreateNewProduct() {
      loginAsAdmin();
      clickOnLeftSectionMenuWithName("Catalog");
      click(By.linkText("Add New Product"));
      click(By.xpath("//input[@name='status'][@value=1]"));
      typeTextIn(By.name("name[en]"), "Bloodborne Duck");
      typeTextIn(By.name("code"), "TEST");
      click(By.xpath("//input[@value='1-3']"));
      typeTextIn(By.name("quantity"), "500");
      String path = new File("src/test/resources/bloodborne_duck.png").getAbsolutePath();
      typeTextIn(By.name("new_images[]"), path);
      typeTextIn(By.name("date_valid_from"), "04071992");
      typeTextIn(By.name("date_valid_to"), "01012999");
      click(By.linkText("Information"));
      click(By.name("manufacturer_id"));
      selectDropdownOptionByIndex(By.cssSelector("select[name='manufacturer_id']"), 1);
      typeTextIn(By.name("keywords"), "lol");
      typeTextIn(By.name("short_description[en]"), "kek");
      typeTextIn(By.cssSelector(".trumbowyg-editor"), "A hunter must hunt");
      typeTextIn(By.name("head_title[en]"), "head title");
      typeTextIn(By.name("meta_description[en]"), "meta description");
      click(By.linkText("Prices"));
      typeTextIn(By.name("purchase_price"), "100");
      click(By.name("purchase_price_currency_code"));
      selectDropdownOptionByIndex(By.cssSelector("select[name='purchase_price_currency_code']"), 1);
      typeTextIn(By.name("prices[USD]"), "100");
      typeTextIn(By.name("prices[EUR]"), "89");
      click(By.name("save"));
      defaultWait(By.linkText("Bloodborne Duck"));
      click(By.linkText("Bloodborne Duck"));
      click(By.name("delete"));
      wait.until(ExpectedConditions.alertIsPresent());
      wd.switchTo().alert().accept();
      wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Rubber Ducks")));
   }
   
   private void assertTextIsCrossedOut(By by) {
      assert wd.findElement(by).getCssValue("text-decoration").contains("line-through");
   }
   
   private void assertTextIsBold(By by) {
      assert wd.findElement(by).getTagName().equals("strong");
   }
   
   private void assertElementColorIs(String rgbaContainer, Color color) {
      String[] colors = rgbaContainer.split(", ");
      if (GREY.equals(color)) {
         for (int i = 1; i < 3; i++)
            assertEquals(colors[0], colors[i]);
      } else if (RED.equals(color)) {
         assert Integer.parseInt(colors[0]) > 0;
         assert (Integer.parseInt(colors[1]) == Integer.parseInt(colors[2])) & Integer.parseInt(colors[1]) == 0;
      }
   }
   
   private float getFontSize(By by) {
      return Float.parseFloat(wd.findElement(by).getCssValue("font-size").replace("px", ""));
   }
   
   private String getColorFrom(By locator) {
      String coloredText = wd.findElement(locator).getCssValue("color").replace(")", "");
      return coloredText.contains("rgba") ? coloredText.replace("rgba(", "") :
        coloredText.replace("rgb(", "");
   }
}
