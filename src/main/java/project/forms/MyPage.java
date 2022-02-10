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

    private final String patten = "//*[@id='post%s_%d']%s";
    private final String ownerId = CredentialsManager.getValue("owner_id");

    private Label post;
    private Label content;
    private TextBox postText;
    private Link photo;
    private Button postLikeBtm;
    private Label comment;
    private final Button nextComment = new Button(By.xpath("//*[@class='js-replies_next_label']"), "Next comment button");
    private final Link photoSrc = new Link(By.xpath("//*[@id='pv_photo']//img[@src]"), "Photo src");
    private final Button photoClose = new Button(By.xpath("//*[@class='pv_close_btn']"), "Photo src");

    public MyPage() {
        super(locator, "My page");
    }

    public void postInit(int postId) {
        post = new Label(By.xpath(String.format(patten, ownerId, postId, "")), "First post");
        content = new Label(By.xpath(String.format(patten, ownerId, postId,  "//*[@class='_post_content']")), "Post content");
        postText = new TextBox(By.xpath(String.format(patten, ownerId, postId,  "//*[contains(@class,'wall_post_text')]")), "First post text");
        photo = new Link(By.xpath(String.format(patten, ownerId, postId,  "//*[contains(@href,'/photo')]")), "Photo link");
        postLikeBtm = new Button(By.xpath(String.format(patten, ownerId, postId, "//*[contains(@class,'_like_button')]")), "Like button");
        comment = new Label(By.xpath(String.format(patten, ownerId, postId,  "//*[contains(@id,'post')][1]")), "Comment post");
    }

    public void commentInit(int postId) {
        comment = new Label(By.xpath(String.format(patten, ownerId, postId,  "")), "Comment post");
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

    public String getCommentAuthorId() {
        comment.scrollTo(false);
        comment.waitForVisible();
        return comment.getAttribute("data-answering-id");
    }

    public void clickCommentBtm() {
        nextComment.scrollTo(false);
        nextComment.click();
    }

    public void clickLikeBtm() {
        postLikeBtm.click();
    }

    public boolean isCommentDisplayed() {
        content.waitForHidden();
        return content.isDisplayed();
    }

    public boolean isPostDisplayed() {
        post.waitForVisible();
        return post.isDisplayed();
    }
}
