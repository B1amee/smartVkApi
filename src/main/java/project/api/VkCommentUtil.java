package project.api;

import project.models.VkComment;
import project.models.VkPost;

import static io.restassured.RestAssured.given;

public class VkCommentUtil extends VkAPIUtils {

    public static VkComment createComment(VkPost vkPost, String massage) {
        log.info("Create comment to post: " + vkPost.toString() + ", message: " + massage);
        String request = String.format("%saccess_token=%s&v=%s", EndPoints.WALL_CREATE_COMMENT, token, v);
        resp = given()
                .queryParam("owner_id", ownerId)
                .queryParam("post_id", vkPost.getPostId())
                .queryParam("message", massage)
                .post(request);
        log.info(resp.asString());
        return VkJsonPathUtil.getVkComment(resp.asString(), "response");
    }

}
