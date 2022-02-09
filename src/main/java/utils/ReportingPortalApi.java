package utils;

import models.VkPost;
import models.photo.VkPhoto;
import utils.api.VkCommentUtil;
import utils.api.VkLikesUtil;
import utils.api.VkPhotoUtil;
import utils.api.VkPostUtil;

import java.util.List;

public class ReportingPortalApi {

    public static VkPost createPost(String text) {
        return VkPostUtil.createPost(text);
    }

    public static List<VkPhoto> savePhoto() {
        return VkPhotoUtil.savePhoto();
    }

    public static void editPost(VkPost vkPost, String randomText, VkPhoto vkPhoto) {
        VkPostUtil.editPost(vkPost, randomText, vkPhoto);
    }

    public static List<String> getLikes(VkPost vkPost) {
        return VkLikesUtil.getPostLikes(vkPost);
    }

    public static String deletePost(VkPost vkPost) {
        return VkPostUtil.deletePost(vkPost);
    }

    public static void createComment(VkPost vkPost, String text) {
        VkCommentUtil.createComment(vkPost, text);
    }
}
