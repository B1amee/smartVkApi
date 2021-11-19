package utils;

import models.VkPost;

import java.util.List;

import static io.restassured.RestAssured.given;

public class VkLikesUtil extends VkAPIUtils {

    public static List<String> getPostLikes(VkPost vkPost) {
        String request = String.format("%saccess_token=%s&v=%s", EndPoints.LIKES_GET_LIST, token, v);
        resp = given()
                .queryParam("owner_id", ownerId)
                .queryParam("item_id", vkPost.getPostId())
                .queryParam("type", "post")
                .post(request);
        return JsonPathUtil.getListByBody(resp.asString(), "response.items");
    }

}
