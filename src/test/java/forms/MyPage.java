package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.VkAPIUtils;

import java.util.List;

public class MyPage extends Form {

    private final ILabel post = getElementFactory()
            .getLabel(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')][1]"), "First post");
    private final ITextBox postText = getElementFactory()
            .getTextBox(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')][1]//*[contains(@class,'wall_post_text')]"), "First post text");
    private final ILink photo = getElementFactory()
            .getLink(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')][1]//*[contains(@href,'/photo')]"), "Photo link");
    private final IButton postLikeBtm = getElementFactory()
            .getButton(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')][1]//*[contains(@class,'like_btn like')]"), "Like button");
    private final IButton postCommentBtm = getElementFactory()
            .getButton(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')][1]//*[contains(@class,'like_btn comment')]"), "Like button");
    private final ILabel comment = getElementFactory()
            .getLabel(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')][1]//*[contains(@id,\"post\")][1]"), "Comment post");
    private final IButton nextComment = getElementFactory()
            .getButton(By.xpath("//*[@class='js-replies_next_label']"), "Next comment button");

    public MyPage() {
        super(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')]"), "My page");
    }

    public String createPost(String text) {
        return VkAPIUtils.getInstance().createPost(text);
    }

    public String savePhoto() {
        return VkAPIUtils.getInstance().savePhoto().replaceAll("\\[", "").replaceAll("]", "");
    }

    public String editPost(String postId, String randomText, String photoId) {
        return VkAPIUtils.getInstance().editPost(postId, randomText, photoId);
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

    public int deletePost(String postId) {
        return VkAPIUtils.getInstance().deletePost(postId);
    }

    public String getCommentAuthorId() {
        return comment.getAttribute("data-answering-id");
    }

    public String createComment(String postId, String text) {
        return VkAPIUtils.getInstance().createComment(postId, text);
    }

    public void clickCommentBtm() {
        nextComment.clickAndWait();
    }

    public void clickLikeBtm() {
        postLikeBtm.clickAndWait();
    }

    public List<String> getLikes(String postId) {
        return VkAPIUtils.getInstance().getLikes(postId);
    }
}
