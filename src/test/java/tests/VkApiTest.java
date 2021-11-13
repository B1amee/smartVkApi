package tests;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import forms.FeedPage;
import forms.MyPage;
import forms.WelcomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VkApiTest extends BaseTest{

    @Test(priority = 1)
    public void goToSite() {
        WelcomePage welcomePage = new WelcomePage();
        assertTrue(welcomePage.state().waitForDisplayed(), "Welcome Page is not open");
        ISettingsFile jsonDataFile = new JsonSettingsFile("data.json");
        String login = jsonDataFile.getValue("/login").toString();
        String password = jsonDataFile.getValue("/password").toString();
        welcomePage.setLoginAndPassword(login, password);
        welcomePage.clickSignInBtm();
        FeedPage feedPage = new FeedPage();
        assertTrue(feedPage.state().waitForDisplayed(), "Feed Page is not open");
        feedPage.clickMyPage();
        MyPage myPage = new MyPage();
        assertTrue(myPage.state().waitForDisplayed(), "My page is not open");
    }

    @Test(priority = 2)
    public void postTest() {

    }
}
