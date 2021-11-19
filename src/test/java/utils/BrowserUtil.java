package utils;

import static aquality.selenium.browser.AqualityServices.*;

public class BrowserUtil {

    public static void goTo(String url) {
        getBrowser().goTo(url);
    }

    public static void waitForPageToLoad() {
        getBrowser().waitForPageToLoad();
    }

    public static void quit() {
        if (isBrowserStarted()) {
            getBrowser().quit();
        }
    }

}
