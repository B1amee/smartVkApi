package models.photo;

public class VkPhotoSize {

    private int height;
    private String url;
    private String type;
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "VkPhotoSize{" +
                "height=" + height +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", width=" + width +
                '}';
    }
}
