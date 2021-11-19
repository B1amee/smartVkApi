package utils;

import aquality.selenium.core.logging.Logger;
import models.VkLikes;
import models.photo.VkPhoto;
import models.photo.VkPhotoPost;
import models.photo.VkPhotoUpload;
import models.VkPost;

import java.util.List;

import static io.restassured.path.json.JsonPath.*;


public class JsonPathUtil {

    private static final Logger log = Logger.getInstance();

    public static String getValueByBody(String body, String key) {
        log.info("Get " + key + " id from " + body);
        return from(body).getString(key);
    }

    public static List<String> getListByBody(String body, String key) {
        log.info("Get likes list by " + key + " from " + body);

        return from(body).getList(key, String.class);
    }

    public static VkPost getVkPost(String body, String path) {
        log.info("Get VkPost by " + path + " from " + body);
        return from(body).getObject(path, VkPost.class);
    }

    public static VkPhotoUpload getVkPhotoUpload(String body, String path) {
        log.info("Get VkPhotoUpload by " + path + " from " + body);
        return from(body).getObject(path, VkPhotoUpload.class);
    }

    public static VkPhotoPost getVkPhoto(String body) {
        log.info("Get VkPhotoPost by from " + body);
        return from(body).getObject(".", VkPhotoPost.class);
    }

    public static VkLikes getVkLikes(String body, String path) {
        log.info("Get getVkLikes by " + path + " from " + body);
        return from(body).getObject(path, VkLikes.class);
    }

    public static List<VkPhoto> getVkPhotos(String body, String path) {
        log.info("Get getVkPhotos by " + path + " from " + body);
        return from(body).getList(path, VkPhoto.class);
    }

}
