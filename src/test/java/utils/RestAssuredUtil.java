package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Post;
import models.User;
import org.apache.http.HttpStatus;

import java.util.*;

import static io.restassured.RestAssured.*;

public class RestAssuredUtil {
    private static RestAssuredUtil instance;

    private Response resp;

    public static RestAssuredUtil getInstance() {
        if (instance == null) {
            init();
        }
        return instance;
    }

    private static void init() {
        instance = new RestAssuredUtil();
        String url = ConfigManager.getProperties("url");
        LoggerUtil.info(RestAssuredUtil.class, "Init to url: " + url);
        requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();
    }

    public List<Post> getAllPosts(String path) {
        resp = get(path);
        return resp.as(new TypeRef<>() {
        });
    }

    public Post getPostByPath(String path) {
        resp = get(path);
        return resp.then().extract().body().as(Post.class);
    }

    public Post createPost(Post post, String path) {
        resp = given().contentType(ContentType.JSON).body(post).when().post(path);
        return resp.then().extract().as(Post.class);
    }

    public String getBody(String path) {
        resp = get(path);
        return resp.asString();
    }

    public void statusCodeIsOk() {
        resp.then().statusCode(HttpStatus.SC_OK);
    }

    public void statusCodeIsNotFound() {
        resp.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    public void statusCodeIsCreate() {
        resp.then().statusCode(HttpStatus.SC_CREATED);
    }

    public List<User> getAllUsers(String path) {
        resp = get(path);
        return resp.as(new TypeRef<>() {
        });
    }

    public void ContentTypeIsJson() {
        resp.then().contentType(ContentType.JSON);
    }

    public User getUserByPath(String path) {
        resp = get(path);
        return resp.then().extract().body().as(User.class);
    }
}
