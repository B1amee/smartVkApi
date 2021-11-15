package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected final Logger log = Logger.getInstance();

    @BeforeTest
    public void beforeTest() {
        getBrowser().goTo("https://vk.com/");
        getBrowser().waitForPageToLoad();
    }

    @AfterTest
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            getBrowser().quit();
        }
    }

    private Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
