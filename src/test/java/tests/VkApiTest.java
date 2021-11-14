package tests;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import forms.FeedPage;
import forms.MyPage;
import forms.WelcomePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class VkApiTest extends BaseTest {

    @Test(priority = 1)
    public void goToSite() {
        WelcomePage welcomePage = new WelcomePage();
        assertTrue(welcomePage.state().waitForDisplayed(), "Welcome Page is not open"); // 1
        ISettingsFile jsonDataFile = new JsonSettingsFile("data.json");
        String login = jsonDataFile.getValue("/login").toString();
        String password = jsonDataFile.getValue("/password").toString();
        welcomePage.setLoginAndPassword(login, password);
        welcomePage.clickSignInBtm();
        FeedPage feedPage = new FeedPage();
        assertTrue(feedPage.state().waitForDisplayed(), "Feed Page is not open"); // 2
        feedPage.clickMyPage();
        MyPage myPage = new MyPage();
        assertTrue(myPage.state().waitForDisplayed(), "My page is not open"); // 3
        String randomText = RandomStringUtils.randomAlphabetic(Integer.parseInt(jsonDataFile.getValue("/letter_count").toString()));
        String postId = myPage.createPost(randomText);
        assertFalse(postId.isEmpty(), "Post do not created"); // 4
        String userId = jsonDataFile.getValue("/owner_id").toString();
        String fullPostId = myPage.getPostId();
        String postText = myPage.getPostText();
        assertEquals(postText, randomText, "Post text not equals");
        assertTrue(fullPostId.contains(userId) && fullPostId.contains(postId), "Post id is uncorrected");
        assertEquals(myPage.getPostText(), randomText, "Post text not equals");// 5
        randomText = RandomStringUtils.randomAlphabetic(Integer.parseInt(jsonDataFile.getValue("/letter_count").toString()));
        String expPhotoId = myPage.savePhoto();
        myPage.editPost(postId, randomText, expPhotoId); // 6
        String actPhotoId = myPage.getPhotoId();
        assertEquals(myPage.getPostText(), randomText, "Edit post text not equals"); //7
        assertTrue(actPhotoId.contains(expPhotoId), "Photo is incorrect"); //7
    }
}
