package tests;

import aquality.selenium.core.logging.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.BrowserUtil;
import utils.DataManager;


public class BaseTest {

    protected final Logger log = Logger.getInstance();

    @BeforeTest
    public void beforeTest() {
        String url = DataManager.getValue("/url");
        BrowserUtil.goTo(url);
        BrowserUtil.waitForPageToLoad();
    }

    @AfterTest
    public void afterTest() {
        BrowserUtil.quit();
    }

}
