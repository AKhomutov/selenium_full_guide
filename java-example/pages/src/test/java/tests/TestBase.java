package tests;

import app.ApplicationManager;
import org.junit.After;
import org.junit.Before;

public class TestBase {
   
   ApplicationManager app = new ApplicationManager();
   
   @Before
   public void setUp() {
      app.init();
   }
   
   @After
   public void tearDown() {
      app.stop();
   }
}
