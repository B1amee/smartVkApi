package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void beforeTest() {
        getBrowser().goTo("https://vk.com/");
        getBrowser().waitForPageToLoad();
    }

    @AfterTest
    public void afterTest() {
        getBrowser().quit();
    }

    private Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
