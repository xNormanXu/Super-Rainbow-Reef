package SuperRainbowReef;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pop extends Object {

    private int score = 0;
    private int level = 1;
    private double angle = Math.PI / 4;
    private double speed = 15.0;
    private String direction = "downToUp-leftToRight";

    // pop constructor
    public Pop(int x, int y, String type) { super(x, y, type); }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public double getAngle() { return angle; }

    public void setAngle(double angle) { this.angle = angle; }

    public double getSpeed() { return speed; }

    public void setSpeed(double speed) { this.speed = speed; }

    public String getDirection() { return direction; }

    public void setDirection(String direction) { this.direction = direction; }

    @Override
    public void draw(Graphics g) {
        if(this.isLive()) {
            BufferedImage img = null;

            try{
                img = ImageIO.read(new File("resource\\image\\Pop_strip45.png"));
                img = img.getSubimage(0, 0, 35, 35);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }

            this.setImg(img);
            this.setWidth(img.getWidth(null));
            this.setHeight(img.getHeight(null));

            g.drawImage(img, this.getX(), this.getY(), null);
        }
    }

    @Override
    public void hit(Pop pop) {
        // change in x and y coordinate
        int dx = (int) Math.abs(Math.cos(pop.getAngle()) * pop.getSpeed());
        int dy = (int) (Math.sin(pop.getAngle()) * pop.getSpeed());

        // pop from down to up, left to right
        if(pop.getDirection() == "downToUp-leftToRight") {
            pop.setX(pop.getX() + dx);
            pop.setY(pop.getY() - dy);
        }

        // pop from down to up, right to left
        if(pop.getDirection() == "downToUp-rightToLeft") {
            pop.setX(pop.getX() - dx);
            pop.setY(pop.getY() - dy);
        }

        // pop from up to down, left to right
        if(pop.getDirection() == "upToDown-leftToRight") {
            pop.setX(pop.getX() + dx);
            pop.setY(pop.getY() + dy);
        }

        // pop from up to down, right to left
        if(pop.getDirection() == "upToDown-rightToLeft") {
            pop.setX(pop.getX() - dx);
            pop.setY(pop.getY() + dy);
        }
    }
}
