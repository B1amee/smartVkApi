package project.api;

import project.models.VkComment;
import project.models.photo.VkPhoto;
import project.models.photo.VkPhotoPost;
import project.models.photo.VkPhotoUpload;
import project.models.VkPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.path.json.JsonPath.*;


public class VkJsonPathUtil {

    private static final Logger log = LoggerFactory.getLogger(VkJsonPathUtil.class);

    public static String getValueByBody(String body, String key) {
        log.info("Get " + key + " id from " + body);
        return from(body).getString(key);
    }

    public static List<Integer> getListByBody(String body, String key) {
        log.info("Get likes list by " + key + " from " + body);
        return from(body).getList(key, Integer.class);
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

    public static List<VkPhoto> getVkPhotos(String body, String path) {
        log.info("Get getVkPhotos by " + path + " from " + body);
        return from(body).getList(path, VkPhoto.class);
    }

    public static Map<String, String> getMap(File file, String path) {
        return from(file).getMap(path);
    }

    public static VkComment getVkComment(String body, String path) {
        return from(body).getObject(path, VkComment.class);
    }
}
