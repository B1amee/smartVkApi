package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.requestSpecification;

abstract class VkAPIUtils {

    protected static String url = DataManager.getValue("api_url");
    protected static String token = DataManager.getValue("token");
    protected static String ownerId = DataManager.getValue("owner_id");
    protected static String v = DataManager.getValue("v");

    protected static final Logger log = Logger.getLogger(JsonPathUtil.class);

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
