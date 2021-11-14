package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.*;

public class VkAPIUtils {
    private static VkAPIUtils instance;

    private String url;
    private String token;
    private String ownerId;
    private String v;
    private final static ISettingsFile jsonDataFile = new JsonSettingsFile("data.json");

    private static final Logger log = Logger.getInstance();

    private Response resp;

    public static VkAPIUtils getInstance() {
        if (instance == null) {
            instance = new VkAPIUtils();
            instance.url = jsonDataFile.getValue("/api_url").toString();
            instance.token = jsonDataFile.getValue("/token").toString();
            instance.ownerId = jsonDataFile.getValue("/owner_id").toString();
            instance.v = jsonDataFile.getValue("/v").toString();
            log.info("Init VkApi " + instance.url);
            requestSpecification = new RequestSpecBuilder().setBaseUri(instance.url).build();
        }
        return instance;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //  https://oauth.vk.com/authorize?client_id=7693362&display=page&scope=wall&response_type=token&v=5.92&state=123456
    // wall.post?owner_id=627657327&message=bdate
    public String createPost(String randomText) {
        String request = String.format("/wall.post?owner_id=%s&message=\"%s\"&access_token=%s&v=%s", ownerId, randomText, token, v);
        log.info("Request " + request);
        resp = post(request);
        return JsonPathUtil.getPostByBody(resp.body().asString(), "response.post_id");
    }

    public int deletePost(String postId) {
        String request = String.format("/wall.delete?owner_id=%s&post_id=%s&access_token=%s&v=%s", ownerId, postId, token, v);
        log.info("Request " + request);
        resp = post(request);
        return JsonPathUtil.getDeleteCode(resp.body().asString());
    }

    public String savePhoto() {
        String request = String.format("/photos.getWallUploadServer?owner_id=%s&access_token=%s&v=%s", ownerId, token, v);
        resp = given().post(request);
        String uploadUrl = JsonPathUtil.getPostByBody(resp.body().asString(), "response.upload_url");
        log.info("Url " + uploadUrl);
        File file = new File(jsonDataFile.getValue("/photo_file").toString());
        try {
            resp = given().contentType("multipart/form-data").multiPart("photo", file).post(new URL(uploadUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String server = JsonPathUtil.getPostByBody(resp.asString(), "server");
        String photo = JsonPathUtil.getPostByBody(resp.asString(), "photo");
        String hash = JsonPathUtil.getPostByBody(resp.asString(), "hash");
        request = String.format("/photos.saveWallPhoto?user_id=%s&server=%s&hash=%s&access_token=%s&v=%s", ownerId, server, hash, token, v);
        resp = given().queryParam("photo", photo).post(request);
        return JsonPathUtil.getPostByBody(resp.asString(), "response.id");
    }

    public String editPost(String postId, String randomText, String photoId) {
        String fullPhotoId = String.format("photo%s_%s", ownerId, photoId);
        String request = String.format("/wall.edit?post_id=%s&message=\"%s\"&attachments=%s&access_token=%s&v=%s", postId, randomText, fullPhotoId, token, v);
        log.info("Request " + request);
        resp = post(request);
        if (resp.body().asString().contains("Captcha needed")) {
            AqualityServices.getBrowser().goTo(JsonPathUtil.getPostByBody(resp.asString(), "captcha_img"));
            String sid = JsonPathUtil.getPostByBody(resp.asString(), "captcha_sid");
            String captcha_img = null;
            try {
                captcha_img = new BufferedReader(new InputStreamReader(System.in)).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            request = String
                    .format("/wall.edit?post_id=%s&message=\"%s\"&attachments=%s&captcha_sid=%s&captcha_key=%s&access_token=%s&v=%s", postId, randomText, fullPhotoId, sid, captcha_img, token, v);
            resp = post(request);
        }
        return JsonPathUtil.getPostByBody(resp.body().asString(), "post_id");
    }
}
