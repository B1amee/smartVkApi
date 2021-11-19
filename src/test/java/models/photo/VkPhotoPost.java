package models.photo;

public class VkPhotoPost {

    private int server;
    private String photo;
    private String hash;

    public int getServer() {
        return server;
    }

    public void setServer(int server) {
        this.server = server;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "VkPhoto{" +
                "server=" + server +
                ", photo='" + photo + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
