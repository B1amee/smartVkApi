package tests;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import forms.FeedPage;
import forms.MyPage;
import forms.WelcomePage;
import models.VkPost;
import models.photo.VkPhoto;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import utils.DataManager;
import utils.OpenCvUtil;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class VkApiTest extends BaseTest {

    @Test(priority = 1)
    public void testVkApi() throws IOException {
        log.info("Step 1: go to https://vk.com/");
        WelcomePage welcomePage = new WelcomePage();
        assertTrue(welcomePage.state().waitForDisplayed(), "Welcome Page is not open");
        log.info("Step 1: complete");

        ISettingsFile jsonDataFile = new JsonSettingsFile("test_data.json");

        log.info("Step 2: Authorize");
        String login = jsonDataFile.getValue("/login").toString();
        String password = jsonDataFile.getValue("/password").toString();
        welcomePage.setLoginAndPassword(login, password);
        welcomePage.clickSignInBtm();
        FeedPage feedPage = new FeedPage();
        assertTrue(feedPage.state().waitForDisplayed(), "Feed Page is not open");
        log.info("Step 2: complete");

        log.info("Step 3: go to My page");
        feedPage.clickMyPage();
        MyPage myPage = new MyPage();
        assertTrue(myPage.state().waitForDisplayed(), "My page is not open");
        log.info("Step 3: complete");

        log.info("Step 4: Using API request, create Post with random text and get post id from response");
        String randomText = RandomStringUtils.randomAlphabetic(Integer.parseInt(jsonDataFile.getValue("/letter_count").toString()));
        VkPost vkPost = myPage.createPost(randomText);
        assertNotNull(vkPost, "Post do not created");
        log.info("Step 4: complete");

        log.info("Step 5: Check wall to find new post from correct user, without refresh the page");
        String userId = jsonDataFile.getValue("/owner_id").toString();
        String fullPostId = myPage.getPostId();
        String postText = myPage.getPostText();
        assertEquals(postText, randomText, "Post text not equals");
        assertTrue(fullPostId.contains(userId) && fullPostId.contains(String.valueOf(vkPost.getPostId())), "Post id is uncorrected");
        assertEquals(myPage.getPostText(), randomText, "Post text not equals");
        log.info("Step 5: complete");

        log.info("Step 6: Edit post with API request, change text and add new photo in the post");
        randomText = RandomStringUtils.randomAlphabetic(Integer.parseInt(jsonDataFile.getValue("/letter_count").toString()));
        VkPhoto expPhoto = myPage.savePhoto().get(0);
        myPage.editPost(vkPost, randomText, expPhoto);
        log.info("Step 6: complete");

        log.info("Step 7: Make sure to change text and added uploading photo(make sure the photo are the same), without refresh the page");
        String actPhotoId = myPage.getPhotoId();
        assertEquals(myPage.getPostText(), randomText, "Edit post text not equals");
        assertTrue(actPhotoId.contains(String.valueOf(expPhoto.getId())), "Photo is incorrect");
        String expPhotoPath = DataManager.getValue("/photo_file");
        String actPhotoPath = myPage.getPhotoUrl();
        assertTrue(OpenCvUtil.checkPhoto(actPhotoPath, expPhotoPath), "Photo src is incorrect");
        myPage.closePhoto();
        log.info("Step 7: complete");

        log.info("Step 8: Using API request, create comment with random text");
        randomText = RandomStringUtils.randomAlphabetic(Integer.parseInt(jsonDataFile.getValue("/letter_count").toString()));
        myPage.createComment(vkPost, randomText);
        log.info("Step 8: complete");

        log.info("Step 9: Make sure that a comment from the correct user has been added to the desired post, without refresh the page");
        myPage.clickCommentBtm();
        String authorId = myPage.getCommentAuthorId();
        assertEquals(authorId, userId, "Author comment id is uncorrected");
        log.info("Step 9: complete");

        log.info("Step 10: Like the post through the UI.");
        myPage.clickLikeBtm();
        log.info("Step 10: complete");

        log.info("Step 1: Make sure that a like from the correct user has been find to the desired post");
        List<String> likesList = myPage.getLikes(vkPost);
        assertTrue(likesList.contains(userId), "Likes do not exist user id");
        log.info("Step 11: complete");

        log.info("Step 12: Using API request, delete post");
        assertEquals(myPage.deletePost(vkPost), "1", "Delete post error");
        log.info("Step 12: complete");

        log.info("Step 13: Make sure that a post has been deleted, without refresh the page");
        String actualPostId = myPage.getPostId();
        assertNotEquals(actualPostId, String.valueOf(vkPost.getPostId()), "Post not delete");
        log.info("Step 13: complete");
    }
}
