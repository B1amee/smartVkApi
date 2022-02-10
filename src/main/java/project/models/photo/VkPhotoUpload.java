package project.models.photo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VkPhotoUpload {
    @JsonProperty("album_id")
    private int albumId;
    @JsonProperty("upload_url")
    private String uploadUrl;
    @JsonProperty("user_id")
    private String userId;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "VkPhotoUpload{" +
                "albumId=" + albumId +
                ", uploadUrl='" + uploadUrl + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
