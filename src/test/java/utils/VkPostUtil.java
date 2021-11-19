package utils;

import models.VkPost;
import models.photo.VkPhoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static io.restassured.RestAssured.given;

public class VkPostUtil extends VkAPIUtils {

    public static VkPost createPost(String massage) {
        String request = String.format("%saccess_token=%s&v=%s", EndPoints.WALL_POST, token, v);
        log.info("Create post " + massage);
        resp = given()
                .queryParam("owner_id", ownerId)
                .queryParam("message", massage)
                .post(request);
        return JsonPathUtil.getVkPost(resp.body().asString(), "response");
    }

    public static String deletePost(VkPost vkPost) {
        String request = String.format("%saccess_token=%s&v=%s",
                EndPoints.WALL_DELETE, token, v);
        log.info("Delete post " + vkPost.toString());
        resp = given()
                .queryParam("owner_id", ownerId)
                .queryParam("post_id", vkPost.getPostId())
                .post(request);
        log.info("Delete post " + resp.asString());
        return JsonPathUtil.getValueByBody(resp.asString(), "response");
    }

    public static void editPost(VkPost vkPost, String massage, VkPhoto vkPhoto) {
        String fullPhotoId = String.format("photo%s_%d", ownerId, vkPhoto.getId());
        String request = String.format("%saccess_token=%s&v=%s", EndPoints.WALL_EDIT, token, v);
        log.info("Edit post " + vkPost.toString());
        resp = given()
                .queryParam("post_id", vkPost.getPostId())
                .queryParam("message", massage)
                .queryParam("attachments", fullPhotoId)
                .post(request);
        if (resp.body().asString().contains("Captcha needed")) {
            BrowserUtil.goTo(JsonPathUtil.getValueByBody(resp.asString(), "captcha_img"));
            String sid = JsonPathUtil.getValueByBody(resp.asString(), "captcha_sid");
            String captcha_img = null;
            try {
                captcha_img = new BufferedReader(new InputStreamReader(System.in)).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            request = String.format("%saccess_token=%s&v=%s", EndPoints.WALL_EDIT, token, v);
            resp = given()
                    .queryParam("", vkPost.getPostId())
                    .queryParam("post_id", massage)
                    .queryParam("message", fullPhotoId)
                    .queryParam("attachments", sid)
                    .queryParam("captcha_sid", captcha_img)
                    .post(request);
        }
    }
}
