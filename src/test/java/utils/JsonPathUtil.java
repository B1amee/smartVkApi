package utils;

import aquality.selenium.core.logging.Logger;

import static io.restassured.path.json.JsonPath.*;


public class JsonPathUtil {

    private static final Logger log = Logger.getInstance();

    public static String getPostByBody(String body, String key) {
        log.info("Get post id from " + body);
        return from(body).getString(key);
    }

    public static String getPostId(String body) {
        log.info("Get post id from " + body);

        return from(body).getString("post_id");
    }

    public static int getDeleteCode(String body) {
        log.info("Get post id from " + body);
        return from(body).getInt("response");
    }
}
