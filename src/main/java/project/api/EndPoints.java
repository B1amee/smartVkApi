package project.api;

public enum EndPoints {
    WALL_POST("/wall.post?"),
    WALL_DELETE("/wall.delete?"),
    WALL_EDIT("/wall.edit?"),
    WALL_CREATE_COMMENT("/wall.createComment?"),
    PHOTOS_GET_UPL_SERVER("/photos.getWallUploadServer?"),
    PHOTOS_SAVE_WALL_PHOTO("/photos.saveWallPhoto?"),
    LIKES_GET_LIST("/likes.getList?");

    private final String endPoint;

    EndPoints(String endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return endPoint;
    }
}
