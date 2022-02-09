package utils.api;

import models.photo.VkPhoto;
import models.photo.VkPhotoPost;
import models.photo.VkPhotoUpload;
import utils.DataManager;
import utils.JsonPathUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static io.restassured.RestAssured.given;

public class VkPhotoUtil extends VkAPIUtils {

    public static List<VkPhoto> savePhoto() {
        log.info("Get upload information ");
        String request = String.format("%saccess_token=%s&v=%s",
                EndPoints.PHOTOS_GET_UPL_SERVER, token, v);
        resp = given()
                .queryParam("owner_id", ownerId)
                .post(request);
        VkPhotoUpload vkPhotoUpload = JsonPathUtil.getVkPhotoUpload(resp.body().asString(), "response");
        log.info("Url " + vkPhotoUpload.getUploadUrl());
        File file = new File(DataManager.getValue("photo_file"));
        try {
            log.info("Upload photo");
            resp = given()
                    .contentType("multipart/form-data")
                    .multiPart("photo", file)
                    .post(new URL(vkPhotoUpload.getUploadUrl()));
        } catch (MalformedURLException e) {
            log.info(e.toString());
        }
        VkPhotoPost vkPhotoPost = JsonPathUtil.getVkPhoto(resp.asString());
        log.info("Save photo");
        request = String.format("%saccess_token=%s&v=%s", EndPoints.PHOTOS_SAVE_WALL_PHOTO, token, v);
        resp = given()
                .queryParam("user_id", ownerId)
                .queryParam("server", vkPhotoPost.getServer())
                .queryParam("hash", vkPhotoPost.getHash())
                .queryParam("photo", vkPhotoPost.getPhoto())
                .post(request);
        return JsonPathUtil.getVkPhotos(resp.asString(), "response");
    }

}
