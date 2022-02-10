package project.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VkPost {

    @JsonProperty("post_id")
    private int postId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "VkPost{" +
                "postId=" + postId +
                '}';
    }
}
