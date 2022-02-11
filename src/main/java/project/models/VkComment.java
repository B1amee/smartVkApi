package project.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class VkComment {

    @JsonProperty("comment_id")
    private int commentId;
    @JsonProperty("parents_stack")
    private int[] parentsStack;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int[] getParentsStack() {
        return parentsStack;
    }

    public void setParentsStack(int[] parentsStack) {
        this.parentsStack = parentsStack;
    }

    @Override
    public String toString() {
        return "VkComment{" +
                "commentId=" + commentId +
                ", parentsStack=" + Arrays.toString(parentsStack) +
                '}';
    }
}
