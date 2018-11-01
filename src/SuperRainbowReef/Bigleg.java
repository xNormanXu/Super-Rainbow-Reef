package SuperRainbowReef;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bigleg extends Object {

    private int speed = 10;
    private String direction;

    // bigleg constructor
    public Bigleg(int x, int y, String type) { super(x, y, type); }

    public int getSpeed() { return speed; }

    public void setSpeed(int speed) { this.speed = speed; }

    public String getDirection() { return direction; }

    public void setDirection(String direction) { this.direction = direction; }

    @Override
    public void draw(Graphics g) {
        if(this.isLive()) {
            BufferedImage img = null;

            try{
                if(this.getType() == "smallBigleg") {
                    img = ImageIO.read(new File("resource\\image\\Bigleg_small_strip24.png"));
                    img = img.getSubimage(0, 0, 40, 40);
                }

                if(this.getType() == "largeBigleg1" || this.getType() == "largeBigleg2") {
                    img = ImageIO.read(new File("resource\\image\\Bigleg_strip24.png"));
                    img = img.getSubimage(0, 0, 80, 80);
                }
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
        Rectangle RBigleg = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        Rectangle RPop = new Rectangle(pop.getX(), pop.getY(), pop.getWidth(), pop.getHeight());

        if(this.getType() == "largeBigleg1") {
            if(this.getX() < 20)
                this.setDirection("right");
            if(this.getX() > 180)
                this.setDirection("left");
            if(this.direction == "left")
                this.setX(this.getX() - this.getSpeed());
            if(this.direction == "right")
                this.setX(this.getX() + this.getSpeed());
        }

        if(this.getType() == "largeBigleg2") {
            if(this.getX() < 380)
                this.setDirection("right");
            if(this.getX() > 540)
                this.setDirection("left");
            if(this.direction == "left")
                this.setX(this.getX() - this.getSpeed());
            if(this.direction == "right")
                this.setX(this.getX() + this.getSpeed());
        }

        if(RBigleg.intersects(RPop)) {
            this.setLive(false);
        }
    }
}
