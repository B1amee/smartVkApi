import models.Post;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import utils.DataManager;
import utils.JsonPathUtil;
import utils.LoggerUtil;

import java.util.*;
import java.util.stream.Collectors;

import static org.testng.Assert.*;
import static utils.RestAssuredUtil.*;
import static utils.JsonPathUtil.*;

public class RestTest extends BaseTest {

    @Test(priority = 1)
    public void getPostsRequest() {
        LoggerUtil.info(this.getClass(), "Test case. Work with REST API (GET/POST)");
        LoggerUtil.info(this.getClass(), "Step 1: Send GET request to get all posts");
        String path = DataManager.getProperties("path1");
        List<Post> postsList = getInstance().getAllPosts(path);
        getInstance().statusCodeIsOk();
        getInstance().ContentTypeIsJson();
        boolean isSorted = postsList.stream().sorted(Comparator.comparingInt(Post::getId)).collect(Collectors.toList()).equals(postsList);
        assertTrue(isSorted, "Step 1: List not sorted");
        LoggerUtil.info(this.getClass(), "Step 1: is complete");

        LoggerUtil.info(this.getClass(), "Step 2: Send GET request to get post #99");
        path = DataManager.getProperties("path2");
        String fileName = DataManager.getProperties("post99");
        Post actualPost = getInstance().getPostByPath(path);
        Post expectedPost = getPostByFile(fileName);
        getInstance().statusCodeIsOk();
        assertEquals(actualPost.getUserId(), expectedPost.getUserId(), "Step 2: userId is not 10");
        assertEquals(actualPost.getId(), expectedPost.getId(), "Step 2: id is not 99");
        assertFalse(actualPost.getTitle().isEmpty(), "Step 2: title is empty");
        assertFalse(actualPost.getBody().isEmpty(), "Step 2: body is empty");
        LoggerUtil.info(this.getClass(), "Step 2: is complete");

        LoggerUtil.info(this.getClass(), "Step 3: Send GET request to get post #150");
        path = DataManager.getProperties("path3");
        String body = getInstance().getBody(path);
        getInstance().statusCodeIsNotFound();
        assertEquals(body, "{}", "Step 3: response body is not empty");
        LoggerUtil.info(this.getClass(), "Step 3: is complete");

        LoggerUtil.info(this.getClass(), "Step 4: Send POST request to create post");
        path = DataManager.getProperties("path4");
        expectedPost = new Post(10, RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(30));
        actualPost = getInstance().createPost(expectedPost, path);
        getInstance().statusCodeIsCreate();
        assertEquals(actualPost.getTitle(), expectedPost.getTitle(), "Step 4: response title is incorrect");
        assertEquals(actualPost.getBody(), expectedPost.getBody(), "Step 4: response body is incorrect");
        assertEquals(actualPost.getUserId(), expectedPost.getUserId(), "Step 4: response userId is incorrect");
        assertTrue(actualPost.getId() != 0, "Step 4: response id is incorrect");
        LoggerUtil.info(this.getClass(), "Step 4: is complete");

        LoggerUtil.info(this.getClass(), "Step 5: Send GET request to get all users");
        path = DataManager.getProperties("path5");
        List<User> usersList = getInstance().getAllUsers(path);
        getInstance().statusCodeIsOk();
        getInstance().ContentTypeIsJson();
        fileName = DataManager.getProperties("user5");
        User expectedUser = JsonPathUtil.getUserByFile(fileName);
        User actualUser = usersList.stream().filter(u -> u.getId() == 5).collect(Collectors.toList()).get(0);
        assertEquals(actualUser.getName(), expectedUser.getName(), "Step 5: user name is incorrect");
        assertEquals(actualUser.getUsername(), expectedUser.getUsername(), "Step 5: user userName is incorrect");
        assertEquals(actualUser.getEmail(), expectedUser.getEmail(), "Step 5: user email is incorrect");
        assertEquals(actualUser.getStreet(), expectedUser.getStreet(), "Step 5: user street is incorrect");
        assertEquals(actualUser.getSuite(), expectedUser.getSuite(), "Step 5: user suite is incorrect");
        assertEquals(actualUser.getCity(), expectedUser.getCity(), "Step 5: user city is incorrect");
        assertEquals(actualUser.getZipcode(), expectedUser.getZipcode(), "Step 5: user zipcode is incorrect");
        assertEquals(actualUser.getLat(), expectedUser.getLat(), "Step 5: user geo lat is incorrect");
        assertEquals(actualUser.getLng(), expectedUser.getLng(), "Step 5: user geo lng is incorrect");
        assertEquals(actualUser.getPhone(), expectedUser.getPhone(), "Step 5: user phone is incorrect");
        assertEquals(actualUser.getWebsite(), expectedUser.getWebsite(), "Step 5: user webSite is incorrect");
        assertEquals(actualUser.getCompanyName(), expectedUser.getCompanyName(), "Step 5: user company name is incorrect");
        assertEquals(actualUser.getCompanyCatchPhrase(), expectedUser.getCompanyCatchPhrase(), "Step 5: user company catch phrase is incorrect");
        assertEquals(actualUser.getCompanyBs(), expectedUser.getCompanyBs(), "Step 5: user company bs is incorrect");
        LoggerUtil.info(this.getClass(), "Step 5: is complete");

        LoggerUtil.info(this.getClass(), "Step 6: Send GET request to get user #5");
        expectedUser = actualUser;
        path = DataManager.getProperties("path6");
        actualUser = getInstance().getUserByPath(path);
        getInstance().statusCodeIsOk();
        assertEquals(actualUser.getName(), expectedUser.getName(), "Step 6: user name is incorrect");
        assertEquals(actualUser.getUsername(), expectedUser.getUsername(), "Step 6: user userName is incorrect");
        assertEquals(actualUser.getEmail(), expectedUser.getEmail(), "Step 6: user email is incorrect");
        assertEquals(actualUser.getStreet(), expectedUser.getStreet(), "Step 6: user street is incorrect");
        assertEquals(actualUser.getSuite(), expectedUser.getSuite(), "Step 6: user suite is incorrect");
        assertEquals(actualUser.getCity(), expectedUser.getCity(), "Step 6: user city is incorrect");
        assertEquals(actualUser.getZipcode(), expectedUser.getZipcode(), "Step 6: user zipcode is incorrect");
        assertEquals(actualUser.getLat(), expectedUser.getLat(), "Step 6: user geo lat is incorrect");
        assertEquals(actualUser.getLng(), expectedUser.getLng(), "Step 6: user geo lng is incorrect");
        assertEquals(actualUser.getPhone(), expectedUser.getPhone(), "Step 6: user phone is incorrect");
        assertEquals(actualUser.getWebsite(), expectedUser.getWebsite(), "Step 6: user webSite is incorrect");
        assertEquals(actualUser.getCompanyName(), expectedUser.getCompanyName(), "Step 6: user company name is incorrect");
        assertEquals(actualUser.getCompanyCatchPhrase(), expectedUser.getCompanyCatchPhrase(), "Step 6: user company catch phrase is incorrect");
        assertEquals(actualUser.getCompanyBs(), expectedUser.getCompanyBs(), "Step 6: user company bs is incorrect");
        LoggerUtil.info(this.getClass(), "Step 6: is complete");
    }
}
