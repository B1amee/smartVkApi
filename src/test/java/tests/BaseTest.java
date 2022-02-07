package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;
import utils.BrowserUtil;
import utils.DataManager;


public class BaseTest {

    protected final Logger log = Logger.getLogger(this.getClass());

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
