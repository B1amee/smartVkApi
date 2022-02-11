package framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.path.json.JsonPath.from;

public class JsonPathUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonPathUtil.class);

    public static String getValueByBody(String body, String key) {
        log.info("Get " + key + " id from " + body);
        return from(body).getString(key);
    }

    public static <T> List<T> getListByBody(String body, String key, Class<T> genericType) {
        log.info("Get " + genericType.getSimpleName() + " list by " + key + " from " + body);
        return from(body).getList(key, genericType);
    }

    public static <T> T getInstance(String body, String path, Class<T> genericType) {
        log.info("Get " + genericType.getSimpleName() + " instance by " + path + " from " + body);
        return from(body).getObject(path, genericType);
    }

    public static <K, V> Map<K, V> getMap(File file, String path) {
        log.info("Get map by " + path + " from file: " + file.getPath());
        return from(file).getMap(path);
    }
}
