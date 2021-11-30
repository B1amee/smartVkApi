package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import models.VkPost;
import models.photo.VkPhoto;
import org.openqa.selenium.By;
import utils.*;

import java.util.List;

public class MyPage extends Form {

    private static final By locator = By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')]");

    private final String patten = "//*[@id='page_wall_posts']//*[contains(@id,'post')][1]%s";

    private final ILabel post = getElementFactory().getLabel(By.xpath(String.format(patten, "")), "First post");
    private final ITextBox postText = getElementFactory().getTextBox(By.xpath(String.format(patten, "//*[contains(@class,'wall_post_text')]")), "First post text");
    private final ILink photo = getElementFactory().getLink(By.xpath(String.format(patten, "//*[contains(@href,'/photo')]")), "Photo link");
    private final IButton postLikeBtm = getElementFactory().getButton(By.xpath(String.format(patten, "//*[contains(@class,'like_btn like')]")), "Like button");
    private final ILabel comment = getElementFactory().getLabel(By.xpath(String.format(patten, "//*[contains(@id,\"post\")][1]")), "Comment post");
    private final IButton nextComment = getElementFactory().getButton(By.xpath("//*[@class='js-replies_next_label']"), "Next comment button");
    private final ILink photoSrc = getElementFactory().getLink(By.xpath("//*[@id='pv_photo']//img[@src]"), "Photo src");
    private final IButton photoClose = getElementFactory().getButton(By.xpath("//*[@class='pv_close_btn']"), "Photo src");

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
        return post.getAttribute("id");
    }

    public String getPostText() {
        return postText.getText().replaceAll("\"", "");
    }

    public String getPhotoId() {
        return photo.getAttribute("data-photo-id");
    }

    public String getPhotoUrl() {
        photo.clickAndWait();
        return photoSrc.getAttribute("src");
    }

    public void closePhoto() {
        photoClose.clickAndWait();
    }

    public String deletePost(VkPost vkPost) {
        return VkPostUtil.deletePost(vkPost);
    }

    public String getCommentAuthorId() {
        return comment.getAttribute("data-answering-id");
    }

    public void createComment(VkPost vkPost, String text) {
        VkCommentUtil.createComment(vkPost, text);
    }

    public void clickCommentBtm() {
        nextComment.clickAndWait();
    }

    public void clickLikeBtm() {
        postLikeBtm.clickAndWait();
    }

    public List<String> getLikes(VkPost vkPost) {
        return VkLikesUtil.getPostLikes(vkPost);
    }
}
