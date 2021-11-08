package utils;

import io.restassured.path.json.JsonPath;
import models.Post;
import models.User;

import java.io.File;

public class JsonPathUtil {

    public static Post getPostByFile(String fileName) {
        return JsonPath.from(new File(fileName)).getObject(".", Post.class);
    }

    public static User getUserByFile(String fileName) {
        return JsonPath.from(new File(fileName)).getObject(".", User.class);
    }
}
