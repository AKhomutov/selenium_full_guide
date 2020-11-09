package tests;

import com.google.common.collect.Ordering;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class TestAdmin extends TestBase {
   
   private By iconPencil = By.cssSelector(".fa-pencil");
   private By rowInCountriesTable = By.cssSelector(".row");
   private By rowInZoneTable = By.cssSelector("#table-zones tr:not(.header)");
   private By rowInGeoZonesTable = By.cssSelector(".dataTable .row");
   private By dropdownGeoZone = By.xpath("(//select[contains(@name,'zone_code]')])");
   
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
   
   @Test
   public void testCountriesAndZonesInAlphabetOrder() {
      loginAsAdmin();
      clickOnLeftSectionMenuWithName("Countries");
      List<String> countries = getListOfCountries();
      boolean countriesAreSorted = Ordering.natural().isOrdered(countries);
      assertTrue(countriesAreSorted);
      int rowsCount = wd.findElements(rowInCountriesTable).size();
      for (int i = 0; i < rowsCount; i++) {
         String numberOfZones = wd.findElements(rowInCountriesTable).get(i).findElement(By.xpath("./td[6]")).getText();
         if (!numberOfZones.equals("0")) {
            wd.findElements(rowInCountriesTable).get(i).findElement(iconPencil).click();
            List<String> zones = getListOfZones();
            boolean zonesAreSorted = Ordering.natural().isOrdered(zones);
            assertTrue(zonesAreSorted);
            wd.navigate().back();
         }
      }
   }
   
   @Test
   public void testGeoZonesInAlphabetOrder() {
      loginAsAdmin();
      clickOnLeftSectionMenuWithName("Geo Zones");
      int geoZoneRows = wd.findElements(rowInGeoZonesTable).size();
      for (int a = 0; a < geoZoneRows; a++) {
         wd.findElements(rowInGeoZonesTable).get(a).findElement(iconPencil).click();
         int geoZoneDropdowns = wd.findElements(dropdownGeoZone).size();
         List<String> zones = new ArrayList<>();
         for (int y = 0; y < geoZoneDropdowns; y++) {
            List<WebElement> options = wd.findElements(dropdownGeoZone).get(y).findElements(By.cssSelector("option"));
            for (WebElement option : options) {
               if ("true".equals(option.getAttribute("selected"))) {
                  String optionText = option.getText();
                  zones.add(optionText);
               }
            }
            boolean geoZonesAreSorted = Ordering.natural().isOrdered(zones);
            assertTrue(geoZonesAreSorted);
         }
         wd.navigate().back();
      }
   }
   
   @Test
   public void testLinksAreOpenedInNewTab() {
      loginAsAdmin();
      clickOnLeftSectionMenuWithName("Countries");
      click(iconPencil);
      List<WebElement> links = wd.findElements(By.className("fa-external-link"));
      int numberOfLinks = links.size();
      for (int i = 0; i < numberOfLinks; i++) {
         String mainWindowBefore = wd.getWindowHandle();
         Set<String> allWindowsBefore = wd.getWindowHandles();
         wd.findElements(By.className("fa-external-link")).get(i).click();
         String mainWindowAfter;
         Set<String> allWindowsAfter = wd.getWindowHandles();
         allWindowsAfter.removeAll(allWindowsBefore);
         mainWindowAfter = allWindowsAfter.iterator().next();
         wd.switchTo().window(mainWindowAfter);
         if (wd.findElements(By.xpath("//a[contains(@class, 'mw-wiki-logo')]")).size() != 0) {
            wait.until(ExpectedConditions.urlContains("wiki"));
         } else {
            wait.until(ExpectedConditions.urlContains("informatica"));
         }
         wd.close();
         wd.switchTo().window(mainWindowBefore);
         wait.until((ExpectedConditions.titleIs("Edit Country | My Store")));
      }
   }
   
   private List<String> getListOfZones() {
      int count = wd.findElements(rowInZoneTable).size() - 1;
      List<String> zones = new ArrayList<>();
      for (int l = 0; l < count; l++) {
         zones.add(wd.findElements(rowInZoneTable).get(l).findElement(By.xpath("./td[3]")).getText());
      }
      return zones;
   }
   
   private List<String> getListOfCountries() {
      List<String> countries = new ArrayList<>();
      int row = wd.findElements(rowInCountriesTable).size();
      for (int i = 0; i < row; i++) {
         countries.add(wd.findElements(rowInCountriesTable).get(i).findElement(By.xpath(".//a")).getText());
      }
      return countries;
   }
}
