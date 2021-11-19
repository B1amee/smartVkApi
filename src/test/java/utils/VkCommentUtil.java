package utils;

import models.VkPost;

import static io.restassured.RestAssured.given;

public class VkCommentUtil extends VkAPIUtils {

    public static void createComment(VkPost vkPost, String massage) {
        String request = String.format("%saccess_token=%s&v=%s", EndPoints.WALL_CREATE_COMMENT, token, v);
        resp = given()
                .queryParam("owner_id", ownerId)
                .queryParam("post_id", vkPost.getPostId())
                .queryParam("message", massage)
                .post(request);
    }

}
