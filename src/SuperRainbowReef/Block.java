package SuperRainbowReef;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Block extends Object {

    // block constructor
    public Block(int x, int y, String type) { super(x, y, type); }

    @Override
    public void draw(Graphics g) {
        if(this.isLive()) {
            BufferedImage img = null;

            try{
                if(this.getType() == "solidBlock")
                    img = ImageIO.read(new File("resource\\image\\Block_solid.png"));
                if(this.getType() == "lifeBlock")
                    img = ImageIO.read(new File("resource\\image\\Block_life.png"));
                if(this.getType() == "purpleBlock")
                    img = ImageIO.read(new File("resource\\image\\Block1.png"));
                if(this.getType() == "yellowBlock")
                    img = ImageIO.read(new File("resource\\image\\Block2.png"));
                if(this.getType() == "redBlock")
                    img = ImageIO.read(new File("resource\\image\\Block3.png"));
                if(this.getType() == "greenBlock")
                    img = ImageIO.read(new File("resource\\image\\Block4.png"));
                if(this.getType() == "cyanBlock")
                    img = ImageIO.read(new File("resource\\image\\Block5.png"));
                if(this.getType() == "blueBlock")
                    img = ImageIO.read(new File("resource\\image\\Block6.png"));
                if(this.getType() == "grayBlock")
                    img = ImageIO.read(new File("resource\\image\\Block7.png"));
                if(this.getType() == "splitBlock")
                    img = ImageIO.read(new File("resource\\image\\Block_split.png"));
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
        Rectangle RBlock = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        Rectangle RPop = new Rectangle(pop.getX(), pop.getY(), pop.getWidth(), pop.getHeight());

        if(RBlock.intersects(RPop)) {

            // pop is above block
            if(pop.getY() + pop.getHeight() - this.getY() < 0.00001) {
                this.setLive(false);

                // up to down, left to right
                if(pop.getDirection() == "upToDown-leftToRight") {
                    pop.setDirection("downToUp-leftToRight");

                    // update angle
                    pop.setAngle(Math.PI - pop.getAngle());

                    // speed up
                    pop.setSpeed(15);
                    return;
                }

                // up to down, right to left
                if(pop.getDirection() == "upToDown-rightToLeft") {
                    pop.setDirection("downToUp-rightToLeft");

                    // update angle
                    pop.setAngle(Math.PI - pop.getAngle());

                    // speed up
                    pop.setSpeed(15);
                    return;
                }
            }

            // pop is below block
            if(this.getY() - pop.getY() < 0.00001) {
                this.setLive(false);

                // down to up, left to right
                if(pop.getDirection() == "downToUp-leftToRight") {
                    pop.setDirection("upToDown-leftToRight");

                    // update angle
                    pop.setAngle(Math.PI - pop.getAngle());

                    // speed up
                    pop.setSpeed(15);
                    return;
                }

                // down to up, right to left
                if(pop.getDirection() == "downToUp-rightToLeft") {
                    pop.setDirection("upToDown-rightToLeft");

                    // update angle
                    pop.setAngle(Math.PI - pop.getAngle());

                    // speed up
                    pop.setSpeed(15);
                    return;
                }
            }

            // pop is on the left of block
            if(pop.getX() + pop.getWidth() - this.getX() < 0.00001) {
                this.setLive(false);

                // up to down, left to right
                if(pop.getDirection() == "upToDown-leftToRight") {
                    pop.setDirection("upToDown-rightToLeft");

                    // update angle
                    pop.setAngle(Math.PI - pop.getAngle());

                    // speed up
                    pop.setSpeed(15);
                    return;
                }

                // down to up, left to right
                if(pop.getDirection() == "downToUp-leftToRight") {
                    pop.setDirection("downToUp-rightToLeft");

                    // update angle
                    pop.setAngle(Math.PI - pop.getAngle());

                    // speed up
                    pop.setSpeed(15);
                    return;
                }
            }

            // pop is on the right of block
            if(this.getX() + this.getWidth() - pop.getX() < 0.00001) {
                this.setLive(false);

                // up to down, right to left
                if(pop.getDirection() == "upToDown-rightToLeft") {
                    pop.setDirection("upToDown-leftToRight");

                    // update angle
                    pop.setAngle(Math.PI - pop.getAngle());

                    // speed up
                    pop.setSpeed(15);
                    return;
                }

                // down to up, right to left
                if(pop.getDirection() == "downToUp-rightToLeft") {
                    pop.setDirection("downToUp-leftToRight");

                    // update angle
                    pop.setAngle(Math.PI - pop.getAngle());

                    // speed up
                    pop.setSpeed(15);
                    return;
                }
            }
        }
    }
}
