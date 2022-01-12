package model.fileData;

public class SpriteAnchor {
    private int x = 0;
    private int y = 0;
    private int rotation = 0;
    private String path = "";
    private int width = 8;
    private int height = 8;
    private boolean isAnimated = false;

    public SpriteAnchor(int x, int y, int rotation, String path, int height, int width, boolean isAnimated){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.path = path;
        this.height = height;
        this.width = width;
        this.isAnimated = isAnimated;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAnimated() {
        return isAnimated;
    }

    public int getRotation() {
        return rotation;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPath() {
        return path;
    }
}
