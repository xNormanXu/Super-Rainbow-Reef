package SuperRainbowReef;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Katch extends Object {

    private int life = 3;

    // katch constructor
    public Katch(int x, int y, String type) { super(x, y, type); }

    public int getLife() { return life; }

    public void setLife(int life) { this.life = life; }

    public void checkBorder() {
        if(this.getX() < 20)
            this.setX(20);
        if(this.getX() > 540)
            this.setX(540);
    }

    @Override
    public void draw(Graphics g) {
        if(this.isLive()) {
            BufferedImage img = null;

            try{
                img = ImageIO.read(new File("resource\\image\\Katch_strip24.png"));
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }

            img = img.getSubimage(0, 0, 80, 30);
            this.setImg(img);
            this.setWidth(img.getWidth(null));
            this.setHeight(img.getHeight(null));
            this.checkBorder();

            g.drawImage(img, this.getX(), this.getY(), null);
        }
    }

    // draw lives of katch
    public void drawLife(Graphics g) {
        if(this.isLive()) {
            BufferedImage img = null;

            try{
                img = ImageIO.read(new File("resource\\image\\Katch_small.png"));
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }

            for(int i = 0; i <this.getLife(); i++)
                g.drawImage(img, i * 40 + 20, 440, null);
        }
    }

    @Override
    public void hit(Pop pop) {
        // change in x and y coordinate
        int dx = (int) Math.abs(Math.cos(pop.getAngle()) * pop.getSpeed());
        int dy = (int) (Math.sin(pop.getAngle()) * pop.getSpeed());

        if(this.getType() == "katch") {
            // pop hits katch, up
            if(pop.getX() > this.getX() - pop.getWidth() && pop.getX() < this.getX() + this.getWidth() && pop.getY() > this.getY() - pop.getHeight()) {

                // up to down, left to right
                if(pop.getDirection() == "upToDown-leftToRight") {
                    pop.setX(pop.getX() + dx);
                    pop.setY(pop.getY() - dy);
                    pop.setDirection("downToUp-leftToRight");
                }

                // up to down, right to left
                if(pop.getDirection() == "upToDown-rightToLeft") {
                    pop.setX(pop.getX() - dx);
                    pop.setY(pop.getY() - dy);
                    pop.setDirection("downToUp-rightToLeft");
                }

                // update angle
                pop.setAngle(Math.PI - pop.getAngle());

                // speed up
                pop.setSpeed(15);
            }
        }

        if(pop.getY() > 470) {
            if(this.getLife() == 1) {
                this.setLive(false);
                pop.setLive(false);
            }
            else {
                this.setLife(this.getLife() - 1);
                pop.setX(360);
                pop.setY(380);
                pop.setDirection("downToUp-leftToRight");
            }
        }
    }
}
