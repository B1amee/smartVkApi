package utils.api;

import models.VkPost;
import utils.JsonPathUtil;

import java.util.List;

import static io.restassured.RestAssured.given;

public class VkLikesUtil extends VkAPIUtils {

    public static List<String> getPostLikes(VkPost vkPost) {
        log.info("Get post likes by post: " + vkPost.toString());
        String request = String.format("%saccess_token=%s&v=%s", EndPoints.LIKES_GET_LIST, token, v);
        resp = given()
                .queryParam("owner_id", ownerId)
                .queryParam("item_id", vkPost.getPostId())
                .queryParam("type", "post")
                .post(request);
        return JsonPathUtil.getListByBody(resp.asString(), "response.items");
    }

}
