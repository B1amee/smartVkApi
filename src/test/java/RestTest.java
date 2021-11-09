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
    public void getAllPostsRequest() {
        LoggerUtil.info(this.getClass(), "Test case. Work with REST API (GET/POST)");
        LoggerUtil.info(this.getClass(), "Step 1: Send GET request to get all posts");
        String path = DataManager.getProperties("path1");
        List<Post> postsList = getInstance().getAllPosts(path);
        getInstance().statusCodeIsOk();
        getInstance().ContentTypeIsJson();
        boolean isSorted = postsList.stream().sorted(Comparator.comparingInt(Post::getId)).collect(Collectors.toList()).equals(postsList);
        assertTrue(isSorted, "Step 1: List not sorted");
        LoggerUtil.info(this.getClass(), "Step 1: is complete");
    }

    @Test(priority = 2)
    public void getPostTest() {
        LoggerUtil.info(this.getClass(), "Step 2: Send GET request to get post #99");
        String path = DataManager.getProperties("path2");
        String fileName = DataManager.getProperties("post99");
        Post actualPost = getInstance().getPostByPath(path);
        Post expectedPost = getPostByFile(fileName);
        getInstance().statusCodeIsOk();
        assertEquals(actualPost.getUserId(), expectedPost.getUserId(), "Step 2: userId is not 10");
        assertEquals(actualPost.getId(), expectedPost.getId(), "Step 2: id is not 99");
        assertFalse(actualPost.getTitle().isEmpty(), "Step 2: title is empty");
        assertFalse(actualPost.getBody().isEmpty(), "Step 2: body is empty");
        LoggerUtil.info(this.getClass(), "Step 2: is complete");
    }

    @Test(priority = 3)
    public void getNoneExPostTest() {
        LoggerUtil.info(this.getClass(), "Step 3: Send GET request to get post #150");
        String path = DataManager.getProperties("path3");
        String body = getInstance().getBody(path);
        getInstance().statusCodeIsNotFound();
        assertEquals(body, "{}", "Step 3: response body is not empty");
        LoggerUtil.info(this.getClass(), "Step 3: is complete");
    }

    @Test(priority = 4)
    public void createPostTest() {
        LoggerUtil.info(this.getClass(), "Step 4: Send POST request to create post");
        String path = DataManager.getProperties("path4");
        Post expectedPost = new Post(10, RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(30));
        Post actualPost = getInstance().createPost(expectedPost, path);
        getInstance().statusCodeIsCreate();
        assertEquals(actualPost.getTitle(), expectedPost.getTitle(), "Step 4: response title is incorrect");
        assertEquals(actualPost.getBody(), expectedPost.getBody(), "Step 4: response body is incorrect");
        assertEquals(actualPost.getUserId(), expectedPost.getUserId(), "Step 4: response userId is incorrect");
        assertTrue(actualPost.getId() != 0, "Step 4: response id is incorrect");
        LoggerUtil.info(this.getClass(), "Step 4: is complete");
    }

    @Test(priority = 5)
    public void getAllUsersTest() {
        LoggerUtil.info(this.getClass(), "Step 5: Send GET request to get all users");
        String path = DataManager.getProperties("path5");
        List<User> usersList = getInstance().getAllUsers(path);
        getInstance().statusCodeIsOk();
        getInstance().ContentTypeIsJson();
        User actualUser = usersList.stream().filter(u -> u.getId() == 5).collect(Collectors.toList()).get(0);
        assertEquals(actualUser.getName(), DataManager.getProperties("name"), "Step 5: user name is incorrect");
        assertEquals(actualUser.getUsername(), DataManager.getProperties("username"), "Step 5: user userName is incorrect");
        assertEquals(actualUser.getEmail(), DataManager.getProperties("email"), "Step 5: user email is incorrect");
        assertEquals(actualUser.getStreet(), DataManager.getProperties("street"), "Step 5: user street is incorrect");
        assertEquals(actualUser.getSuite(), DataManager.getProperties("suite"), "Step 5: user suite is incorrect");
        assertEquals(actualUser.getCity(), DataManager.getProperties("city"), "Step 5: user city is incorrect");
        assertEquals(actualUser.getZipcode(), DataManager.getProperties("zipcode"), "Step 5: user zipcode is incorrect");
        assertEquals(actualUser.getLat(), DataManager.getProperties("lat"), "Step 5: user geo lat is incorrect");
        assertEquals(actualUser.getLng(), DataManager.getProperties("lng"), "Step 5: user geo lng is incorrect");
        assertEquals(actualUser.getPhone(), DataManager.getProperties("phone"), "Step 5: user phone is incorrect");
        assertEquals(actualUser.getWebsite(), DataManager.getProperties("website"), "Step 5: user webSite is incorrect");
        assertEquals(actualUser.getCompanyName(), DataManager.getProperties("companyname"), "Step 5: user company name is incorrect");
        assertEquals(actualUser.getCompanyCatchPhrase(), DataManager.getProperties("catchPhrase"), "Step 5: user company catch phrase is incorrect");
        assertEquals(actualUser.getCompanyBs(), DataManager.getProperties("bs"), "Step 5: user company bs is incorrect");
        LoggerUtil.info(this.getClass(), "Step 5: is complete");
        String fileName = DataManager.getProperties("user5");
        DataManager.saveUserByFile(actualUser, fileName);
    }

    @Test(priority = 6)
    public void getUserTest() {
        LoggerUtil.info(this.getClass(), "Step 6: Send GET request to get user #5");
        String fileName = DataManager.getProperties("user5");
        User expectedUser = JsonPathUtil.getUserByFile(fileName);
        String path = DataManager.getProperties("path6");
        User actualUser = getInstance().getUserByPath(path);
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
