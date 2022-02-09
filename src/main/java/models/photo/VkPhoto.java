package models.photo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class VkPhoto {
    @JsonProperty("album_id")
    private int albumId;
    private Date date;
    private long id;
    @JsonProperty("owner_id")
    private String ownerId;
    @JsonProperty("has_tags")
    private boolean hasTags;
    private String access_key;
    private String hash;
    private String uploadUrl;
    private List<VkPhotoSize> sizes;
    private String text;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isHasTags() {
        return hasTags;
    }

    public void setHasTags(boolean hasTags) {
        this.hasTags = hasTags;
    }

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        this.access_key = access_key;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public List<VkPhotoSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<VkPhotoSize> sizes) {
        this.sizes = sizes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "VkPhoto{" +
                "albumId=" + albumId +
                ", date=" + date +
                ", id=" + id +
                ", ownerId='" + ownerId + '\'' +
                ", hasTags=" + hasTags +
                ", access_key='" + access_key + '\'' +
                ", hash='" + hash + '\'' +
                ", uploadUrl='" + uploadUrl + '\'' +
                ", sizes=" + sizes +
                ", text='" + text + '\'' +
                '}';
    }
}
