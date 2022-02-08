package forms;

import elements.Button;
import elements.Label;
import elements.Link;
import elements.TextBox;
import models.VkPost;
import models.photo.VkPhoto;
import org.openqa.selenium.By;
import utils.*;

import java.util.List;

public class MyPage extends BaseForm {

    private static final By locator = By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')]");

    private final String patten = "//*[@id='page_wall_posts']//*[contains(@id,'post')][1]%s";

    private final Label post = new Label(By.xpath(String.format(patten, "")), "First post");
    private final Label content = new Label(By.xpath(String.format(patten, "//*[@class='_post_content']")), "Post content");
    private final TextBox postText = new TextBox(By.xpath(String.format(patten, "//*[contains(@class,'wall_post_text')]")), "First post text");
    private final Link photo = new Link(By.xpath(String.format(patten, "//*[contains(@href,'/photo')]")), "Photo link");
    private final Button postLikeBtm = new Button(By.xpath(String.format(patten, "//*[contains(@class,'_like_button')]")), "Like button");
    private final Label comment = new Label(By.xpath(String.format(patten, "//*[contains(@id,\"post\")][1]")), "Comment post");
    private final Button nextComment = new Button(By.xpath("//*[@class='js-replies_next_label']"), "Next comment button");
    private final Link photoSrc = new Link(By.xpath("//*[@id='pv_photo']//img[@src]"), "Photo src");
    private final Button photoClose = new Button(By.xpath("//*[@class='pv_close_btn']"), "Photo src");

    public MyPage() {
        super(locator, "My page");
    }

    public VkPost createPost(String text) {
        return VkPostUtil.createPost(text);
    }

    public List<VkPhoto> savePhoto() {
        return VkPhotoUtil.savePhoto();
    }

    public void editPost(VkPost vkPost, String randomText, VkPhoto vkPhoto) {
        VkPostUtil.editPost(vkPost, randomText, vkPhoto);
    }

    public String getPostId() {
        post.waitForExist();
        return post.getAttribute("id");
    }

    public String getPostText() {
        return postText.getText().replaceAll("\"", "");
    }

    public String getPhotoId() {
        return photo.getAttribute("data-photo-id");
    }

    public String getPhotoUrl() {
        photo.click();
        return photoSrc.getAttribute("src");
    }

    public void closePhoto() {
        photoClose.click();
    }

    public String deletePost(VkPost vkPost) {
        return VkPostUtil.deletePost(vkPost);
    }

    public String getCommentAuthorId() {
        comment.scrollTo(false);
        comment.waitForVisible();
        return comment.getAttribute("data-answering-id");
    }

    public void createComment(VkPost vkPost, String text) {
        VkCommentUtil.createComment(vkPost, text);
    }

    public void clickCommentBtm() {
        nextComment.scrollTo(false);
        nextComment.click();
    }

    public void clickLikeBtm() {
        postLikeBtm.click();
    }

    public List<String> getLikes(VkPost vkPost) {
        return VkLikesUtil.getPostLikes(vkPost);
    }

    public boolean isPostDisplayed() {
        content.waitForHidden();
        return content.isDisplayed();
    }
}
