package forms;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.VkAPIUtils;

public class MyPage extends Form {

    private ILabel post = getElementFactory().getLabel(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')][1]"), "First post");
    private ITextBox postText = getElementFactory()
            .getTextBox(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')][1]//*[contains(@class,'wall_post_text')]"), "First post text");
    private ILink photo = getElementFactory().getLink(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')][1]//*[contains(@href,'/photo')]"), "Photo link");
//    private ILink photoSrc = getElementFactory().getLink(By.xpath("//*[@id='pv_photo']/img"), "Photo src");

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

}
