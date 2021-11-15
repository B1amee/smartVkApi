package utils;

import aquality.selenium.core.logging.Logger;
import java.util.List;

import static io.restassured.path.json.JsonPath.*;


public class JsonPathUtil {

    private static final Logger log = Logger.getInstance();

    public static String getValueByBody(String body, String key) {
        log.info("Get key id from " + body);
        return from(body).getString(key);
    }

    public static List<String> getListByBody(String body, String key) {
        log.info("Get list id from " + body);

        return from(body).getList(key, String.class);
    }
}
