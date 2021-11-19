package utils;

import aquality.selenium.core.logging.Logger;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import models.photo.VkPhoto;
import models.photo.VkPhotoPost;
import models.photo.VkPhotoUpload;
import models.VkPost;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static io.restassured.RestAssured.*;

abstract class VkAPIUtils {

    protected static String url = DataManager.getValue("/api_url");
    protected static String token = DataManager.getValue("/token");
    protected static String ownerId = DataManager.getValue("/owner_id");
    protected static String v = DataManager.getValue("/v");

    protected static final Logger log = Logger.getInstance();

    static {
        log.info("Init VkApi " + VkAPIUtils.url);
        requestSpecification = new RequestSpecBuilder().setBaseUri(VkAPIUtils.url).build();
    }

    protected static Response resp;

    public static void setOwnerId(String ownerId) {
        VkAPIUtils.ownerId = ownerId;
    }

    public static void setV(String v) {
        VkAPIUtils.v = v;
    }

    public static void setUrl(String url) {
        VkAPIUtils.url = url;
    }

    public static void setToken(String token) {
        VkAPIUtils.token = token;
    }

}
