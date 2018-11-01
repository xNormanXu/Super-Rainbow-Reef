package SuperRainbowReef;

import java.awt.*;

public abstract class Object {

    // location of object
    private int x;
    private int y;
    // object lives or not
    private boolean live = true;
    // image of object
    private Image img;
    // size of object
    private int width;
    private int height;
    // type of object(block, wall ……)
    private String type;
    // object constructor
    public Object(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public boolean isLive() { return live; }

    public void setLive(boolean live) { this.live = live; }

    public Image getImg() { return img; }

    public void setImg(Image img) { this.img = img; }

    public int getWidth() { return width; }

    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    // draw each object
    public abstract void draw(Graphics g);

    // check if pop hits objects
    public abstract void hit(Pop pop);
}
