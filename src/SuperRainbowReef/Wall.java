package SuperRainbowReef;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wall extends Object {

    // wall constructor
    public Wall(int x, int y, String type) { super(x, y, type); }

    @Override
    public void draw(Graphics g) {
        if(this.isLive()) {
            BufferedImage img = null;

            try{
                img = ImageIO.read(new File("resource\\image\\Wall.png"));
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
        // hit wall on the left
        if(pop.getX() < 20) {

            // up to down, right to left
            if(pop.getDirection() == "upToDown-rightToLeft") {
                // update direction
                pop.setDirection("upToDown-leftToRight");
                // update angle
                pop.setAngle(Math.PI - pop.getAngle());
                // speed down
                pop.setSpeed(pop.getSpeed() - 2);
            }

            // down to up, right to left
            if(pop.getDirection() == "downToUp-rightToLeft") {
                // update direction
                pop.setDirection("downToUp-leftToRight");
                // update angle
                pop.setAngle(Math.PI - pop.getAngle());
                // speed down
                pop.setSpeed(pop.getSpeed() - 2);
            }
        }

        // hit wall on the right
        if(pop.getX() > 580) {

            // up to down, left to right
            if(pop.getDirection() == "upToDown-leftToRight") {
                // update direction
                pop.setDirection("upToDown-rightToLeft");
                // update angle
                pop.setAngle(Math.PI - pop.getAngle());
                // speed down
                pop.setSpeed(pop.getSpeed() - 2);
            }

            // down to up, left to right
            if(pop.getDirection() == "downToUp-leftToRight") {
                // update direction
                pop.setDirection("downToUp-rightToLeft");
                // update angle
                pop.setAngle(Math.PI - pop.getAngle());
                // speed down
                pop.setSpeed(pop.getSpeed() - 2);
            }
        }

        // hit wall on the top
        if(pop.getY() < 20) {

            // down to up, left to right
            if(pop.getDirection() == "downToUp-leftToRight") {
                // update direction
                pop.setDirection("upToDown-leftToRight");
                // update angle
                pop.setAngle(Math.PI - pop.getAngle());
                // speed down
                pop.setSpeed(pop.getSpeed() - 2);
            }

            // down to up, right to left
            if(pop.getDirection() == "downToUp-rightToLeft") {
                // update direction
                pop.setDirection("upToDown-rightToLeft");
                // update angle
                pop.setAngle(Math.PI - pop.getAngle());
                // speed down
                pop.setSpeed(pop.getSpeed() - 2);
            }
        }
    }
}
