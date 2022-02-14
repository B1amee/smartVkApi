import framework.utils.ImageUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import project.utils.api.VkCommentUtil;
import project.utils.api.VkLikesUtil;
import project.utils.api.VkPhotoUtil;
import project.utils.api.VkPostUtil;
import project.forms.FeedPage;
import project.forms.MyPage;
import project.forms.WelcomePage;
import project.models.VkComment;
import project.models.VkPost;
import project.models.photo.VkPhoto;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class VkApiTest extends BaseTest {

    @Test(priority = 1)
    @Parameters({"photo_file", "deviation"})
    public void testVkApi(String expPhotoPath, int deviation) throws IOException {
        log.info("Step 1: go to https://vk.com/");
        WelcomePage welcomePage = new WelcomePage();
        assertTrue(welcomePage.isDisplayed(), "Welcome Page is not open");
        log.info("Step 1: complete");


        log.info("Step 2: Authorize");
        welcomePage.setLoginAndPassword(login, password);
        welcomePage.clickSignInBtm();
        FeedPage feedPage = new FeedPage();
        assertTrue(feedPage.isDisplayed(), "Feed Page is not open");
        log.info("Step 2: complete");

        log.info("Step 3: go to My page");
        feedPage.clickMyPage();
        MyPage myPage = new MyPage();
        assertTrue(myPage.isDisplayed(), "My page is not open");
        log.info("Step 3: complete");

        log.info("Step 4: Using API request, create Post with random text and get post id from response");
        String randomText = RandomStringUtils.randomAlphabetic(letterCount);
        VkPost vkPost = VkPostUtil.createPost(randomText);
        assertNotNull(vkPost, "Post do not created");
        log.info("Step 4: complete");

        log.info("Step 5: Check wall to find new post from correct user, without refresh the page");
        myPage.postInit(userId, vkPost.getPostId());
        assertTrue(myPage.isPostDisplayed(), "Post id is uncorrected"); //fullPostId.contains(userId) && fullPostId.contains(String.valueOf(vkPost.getPostId())
        assertEquals(myPage.getPostText(), randomText, "Post text not equals");
        log.info("Step 5: complete");

        log.info("Step 6: Edit post with API request, change text and add new photo in the post");
        randomText = RandomStringUtils.randomAlphabetic(letterCount);
        VkPhoto expPhoto = VkPhotoUtil.savePhoto(expPhotoPath).get(0);
        VkPostUtil.editPost(vkPost, randomText, expPhoto);
        log.info("Step 6: complete");

        log.info("Step 7: Make sure to change text and added uploading photo(make sure the photo are the same), without refresh the page");
        String actPhotoId = myPage.getPhotoId();
        assertEquals(myPage.getPostText(), randomText, "Edit post text not equals");
        assertTrue(actPhotoId.contains(String.valueOf(expPhoto.getId())), "Photo is incorrect");
        String actPhotoPath = myPage.getPhotoUrl();
        assertTrue(ImageUtil.isEqualsImages(actPhotoPath, expPhotoPath, deviation), "Photo src is incorrect");
        myPage.closePhoto();
        log.info("Step 7: complete");

        log.info("Step 8: Using API request, create comment with random text");
        randomText = RandomStringUtils.randomAlphabetic(letterCount);
        VkComment vkComment = VkCommentUtil.createComment(vkPost, randomText);
        log.info("Step 8: complete");

        log.info("Step 9: Make sure that a comment from the correct user has been added to the desired post, without refresh the page");
        myPage.commentInit(userId, vkComment.getCommentId());
        myPage.clickCommentBtm();
        assertTrue(myPage.isCommentDisplayed(), "Comment  do not created");
        assertEquals(myPage.getCommentText(), randomText, "Comment text not equals");
        log.info("Step 9: complete");

        log.info("Step 10: Like the post through the UI.");
        myPage.clickLikeBtm();
        log.info("Step 10: complete");

        log.info("Step 11: Make sure that a like from the correct user has been find to the desired post");
        List<Integer> likesList = VkLikesUtil.getPostLikes(vkPost);
        assertTrue(likesList.contains(userId), "Likes do not exist user id");
        log.info("Step 11: complete");

        log.info("Step 12: Using API request, delete post");
        assertEquals(VkPostUtil.deletePost(vkPost), "1", "Delete post error");
        log.info("Step 12: complete");

        log.info("Step 13: Make sure that a post has been deleted, without refresh the page");
        assertFalse(myPage.isDeletePostDisplayed(), "Post not delete");
        log.info("Step 13: complete");
    }
}
