package project.forms;

import framework.elements.Button;
import framework.elements.Label;
import framework.elements.Link;
import framework.elements.TextBox;
import framework.BaseForm;
import framework.utils.ConfigManager;
import framework.utils.CredentialsManager;
import org.openqa.selenium.By;

public class MyPage extends BaseForm {

    private static final By locator = By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')]");

    private String commentPattern;

    private Label post;
    private TextBox postText;
    private Link photo;
    private Button postLikeBtm;
    private Label comment;
    private TextBox commentText;
    private final Button nextComment = new Button(By.xpath("//*[@class='js-replies_next_label']"), "Next comment button");
    private final Link photoSrc = new Link(By.xpath("//*[@id='pv_photo']//img[@src]"), "Photo src");
    private final Button photoClose = new Button(By.xpath("//*[@class='pv_close_btn']"), "Photo close");

    public MyPage() {
        super(locator, "My page");
    }

    public void postInit(int ownerId, int postId) {
        String patten = "//*[@id='post%s_%d']%s";
        commentPattern = String.format(patten, ownerId, postId, "//*[@id='post%s_%d']%s");
        post = new Label(By.xpath(String.format(patten, ownerId, postId, "")), "First post");
        postText = new TextBox(By.xpath(String.format(patten, ownerId, postId,  "//*[contains(@class,'wall_post_text')]")), "Post text");
        photo = new Link(By.xpath(String.format(patten, ownerId, postId,  "//*[contains(@href,'/photo')]")), "Photo link");
        postLikeBtm = new Button(By.xpath(String.format(patten, ownerId, postId, "//*[contains(@class,'_like_button')]")), "Like button");
    }

    public void commentInit(int ownerId, int commentId) {
        comment = new Label(By.xpath(String.format(commentPattern, ownerId, commentId,  "")), "Comment post");
        commentText = new TextBox(By.xpath(String.format(commentPattern, ownerId, commentId,  "//*[contains(@class,'wall_reply_text')]")), "Comment text");
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

    public String getCommentText() {
        commentText.scrollTo(false);
        commentText.waitForVisible();
        return commentText.getText();
    }

    public void clickCommentBtm() {
        nextComment.scrollTo(false);
        nextComment.click();
    }

    public void clickLikeBtm() {
        postLikeBtm.click();
    }

    public boolean isDeletePostDisplayed() {
        post.waitForHidden();
        return post.isDisplayed();
    }

    public boolean isCommentDisplayed() {
        comment.waitForVisible();
        return comment.isDisplayed();
    }

    public boolean isPostDisplayed() {
        post.waitForVisible();
        return post.isDisplayed();
    }
}
