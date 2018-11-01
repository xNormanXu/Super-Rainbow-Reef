package SuperRainbowReef;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Panel extends JPanel implements KeyListener, Runnable {

    // help pop reset location when level changes
    private boolean flag = false;
    // blocks in level 1
    private Vector<Block> blocks1;
    // blocks in level 2
    private Vector<Block> blocks2;
    // biglegs(small) in level 1
    private Vector<Bigleg> biglegs1;
    // biglegs(large) in level 2
    private Vector<Bigleg> biglegs2;
    // font in level 1 and level 2
    private Font font;
    // katch in level 1 and level 2
    private Katch katch;
    // pop in level 1 and level 2
    private Pop pop;
    // wall in level 1 and level 2
    private Vector<Wall> walls;

    // panel constructor
    public Panel() {
        blocks1 = new Vector<>();
        blocks2 = new Vector<>();
        biglegs1 = new Vector<>();
        biglegs2 = new Vector<>();
        font = new Font("Arial", Font.BOLD, 20);
        katch = new Katch(320, 420, "katch");
        pop = new Pop(360, 380, "pop");
        walls = new Vector<>();

        this.generateBlock1();
        this.generateBlock2();
        this.generateBigleg1();
        this.generateBigleg2();
        this.generateWall();
    }

    public boolean isFlag() { return flag; }

    public void setFlag(boolean flag) { this.flag = flag; }

    // change level
    public void changeLevel() {
        if(biglegs1.size() == 0) {
            if(pop.getLevel() == 1) {
                pop.setLevel(2);
                this.setFlag(true);
            }
        }
    }

    // reset pop
    public void resetPop() {
        if(this.isFlag()) {
            if(pop.getLevel() == 2) {

                // reset location
                pop.setX(360);
                pop.setY(380);

                // reset direction
                pop.setDirection("downToUp-leftToRight");
                this.setFlag(false);
            }
        }
    }

    // draw blocks, depend on level
    public void drawBlock(Graphics g) {
        if(pop.getLevel() == 1) {
            for(int i = 0; i < blocks1.size(); i++) {
                if(blocks1.get(i).isLive()) {
                    blocks1.get(i).hit(pop);
                    blocks1.get(i).draw(g);
                }
            }
        }

        if(pop.getLevel() == 2) {
            for(int i = 0; i < blocks2.size(); i++) {
                if(blocks2.get(i).isLive()) {
                    blocks2.get(i).hit(pop);
                    blocks2.get(i).draw(g);
                }
            }
        }
    }

    // generate blocks for level 1
    public void generateBlock1() {
        // solid block
        for(int i = 0; i < 12; i++) {
            Block block1 = new Block(100, i * 20 + 20, "solidBlock");
            blocks1.add(block1);
            Block block2 = new Block(220, i * 20 + 20, "solidBlock");
            blocks1.add(block2);
            Block block3 = new Block(380, i * 20 + 20, "solidBlock");
            blocks1.add(block3);
            Block block4 = new Block(500, i * 20 + 20, "solidBlock");
            blocks1.add(block4);
        }

        // life block
        for(int i = 0; i < 2; i++) {
            Block block1 = new Block(i * 40 + 20, 20, "lifeBlock");
            blocks1.add(block1);
            Block block2 = new Block(640 - (i + 1) * 40 - 20, 20, "lifeBlock");
            blocks1.add(block2);
        }

        // purple block
        for(int i = 0; i < 11; i++) {
            Block block1 = new Block(260, (i + 1) * 20, "purpleBlock");
            blocks1.add(block1);
            Block block2 = new Block(340, (i + 1) * 20, "purpleBlock");
            blocks1.add(block2);
        }

        // yellow block
        for(int i = 0; i < 9; i++) {
            Block block1 = new Block(140, (i + 1) * 20 + 60, "yellowBlock");
            blocks1.add(block1);
            Block block2 = new Block(460, (i + 1) * 20 + 60, "yellowBlock");
            blocks1.add(block2);
        }

        // red block
        for(int i = 0; i < 11; i++) {
            Block block1 = new Block(60, (i + 2) * 20, "redBlock");
            blocks1.add(block1);
            Block block2 = new Block(540, (i + 2) * 20, "redBlock");
            blocks1.add(block2);
        }

        // green block
        for(int i = 0; i < 11; i++) {
            Block block1 = new Block(180, (i + 1) * 20, "greenBlock");
            blocks1.add(block1);
            Block block2 = new Block(420, (i + 1) * 20, "greenBlock");
            blocks1.add(block2);
        }

        // blue block
        for(int i = 0; i < 11; i++) {
            Block block1 = new Block(20, (i + 2) * 20, "blueBlock");
            blocks1.add(block1);
            Block block2 = new Block(580, (i + 2) * 20, "blueBlock");
            blocks1.add(block2);
        }

        // gray block
        for(int i = 0; i < 9; i++) {
            Block block = new Block(300, i * 20 + 60, "grayBlock");
            blocks1.add(block);
        }

        // split block
        for(int i = 0; i < 2; i++) {
            Block block1 = new Block(140 + i * 320, 60, "splitBlock");
            blocks1.add(block1);
            Block block2 = new Block(180 + i * 240, 240, "splitBlock");
            blocks1.add(block2);
        }

        for(int i = 0; i < blocks1.size(); i++)
            blocks1.get(i).hit(pop);
    }

    // generate blocks for level 2
    public void generateBlock2() {
        // solid block
        for(int i = 0; i < 4; i++) {
            Block block1 = new Block(260, i * 20 + 20, "solidBlock");
            blocks2.add(block1);
            Block block2 = new Block(300, i * 20 + 20, "solidBlock");
            blocks2.add(block2);
            Block block3 = new Block(340, i * 20 + 20, "solidBlock");
            blocks2.add(block3);
        }

        for(int i = 0; i < 15; i++) {
            Block block1 = new Block(i * 40 + 20, 100, "solidBlock");
            blocks2.add(block1);
            Block block2 = new Block(i * 40 + 20, 120, "solidBlock");
            blocks2.add(block2);
            Block block3 = new Block(i * 40 + 20, 140, "solidBlock");
            blocks2.add(block3);
        }

        // life block
        for(int i = 0; i < 2; i++) {
            Block block1 = new Block(260, i * 20 + 160, "lifeBlock");
            blocks2.add(block1);
            Block block2 = new Block(340, i * 20 + 160, "lifeBlock");
            blocks2.add(block2);
        }

        // purple block
        for(int i = 0; i < 3; i++) {
            Block block1 = new Block(260, i * 20 + 200, "purpleBlock");
            blocks2.add(block1);
            Block block2 = new Block(340, i * 20 + 200, "purpleBlock");
            blocks2.add(block2);
        }

        // yellow block
        for(int i = 0; i < 5; i++) {
            Block block1 = new Block(140, i * 20 + 160, "yellowBlock");
            blocks2.add(block1);
            Block block2 = new Block(460, i * 20 + 160, "yellowBlock");
            blocks2.add(block2);
        }

        // red block
        for(int i = 0; i < 5; i++) {
            Block block1 = new Block(60, i * 20 + 160, "redBlock");
            blocks2.add(block1);
            Block block2 = new Block(540, i * 20 + 160, "redBlock");
            blocks2.add(block2);
        }

        // green block
        for(int i = 0; i < 5; i++) {
            Block block1 = new Block(180, i * 20 + 160, "greenBlock");
            blocks2.add(block1);
            Block block2 = new Block(420, i * 20 + 160, "greenBlock");
            blocks2.add(block2);
        }

        // cyan block
        for(int i = 0; i < 3; i++) {
            Block block1 = new Block(100, i * 20 + 180, "cyanBlock");
            Block block2 = new Block(500, i * 20 + 180, "cyanBlock");
            blocks2.add(block1);
            blocks2.add(block2);
        }

        for(int i = 0; i < 4; i++) {
            Block block1 = new Block(220, i * 20 + 160, "cyanBlock");
            Block block2 = new Block(380, i * 20 + 160, "cyanBlock");
            blocks2.add(block1);
            blocks2.add(block2);
        }

        // blue block
        for(int i = 0; i < 5; i++) {
            Block block1 = new Block(20, i * 20 + 160, "blueBlock");
            blocks2.add(block1);
            Block block2 = new Block(580, i * 20 + 160, "blueBlock");
            blocks2.add(block2);
        }

        // gray block
        for(int i = 0; i < 5; i++) {
            Block block = new Block(300, i * 20 + 160, "grayBlock");
            blocks2.add(block);
        }

        // split block
        for(int i = 0; i < 2; i++) {
            Block block = new Block(i * 400 + 100, 160, "splitBlock");
            blocks2.add(block);
        }

        for(int i = 0; i < blocks2.size(); i++)
            blocks2.get(i).hit(pop);
    }

    // draw biglegs, depend on level
    public void drawBigleg(Graphics g) {
        if(pop.getLevel() == 1) {
            for(int i = 0; i < biglegs1.size(); i++) {
                if(biglegs1.get(i).isLive()) {
                    biglegs1.get(i).hit(pop);
                    biglegs1.get(i).draw(g);
                }
            }
        }

        if(pop.getLevel() == 2) {
            for(int i = 0; i < biglegs2.size(); i++) {
                if(biglegs2.get(i).isLive()) {
                    biglegs2.get(i).hit(pop);
                    biglegs2.get(i).draw(g);
                }
            }
        }
    }

    // generate biglegs for level 1
    public void generateBigleg1() {
        for(int i = 0; i < 3; i++) {
            Bigleg bigleg = new Bigleg(i * 160 + 140, 20, "smallBigleg");
            biglegs1.add(bigleg);
        }
    }

    // generate biglegs for level 2
    public void generateBigleg2() {
        Bigleg bigleg1 = new Bigleg(20, 20, "largeBigleg1");
        Bigleg bigleg2 = new Bigleg(540, 20, "largeBigleg2");

        bigleg1.setDirection("right");
        bigleg2.setDirection("left");

        biglegs2.add(bigleg1);
        biglegs2.add(bigleg2);
    }

    // draw katch for level 1 and level 2
    public void drawKatch(Graphics g) {
        katch.draw(g);
        katch.drawLife(g);
        katch.hit(pop);
    }

    // draw pop for level 1 and level 2
    public void drawPop(Graphics g) {
        // update location of pop
        pop.hit(pop);

        pop.draw(g);
    }

    // draw walls for level 1 and level 2
    public void drawWall(Graphics g) {
        for(int i = 0; i < walls.size(); i++) {
            walls.get(i).hit(pop);
            walls.get(i).draw(g);
        }
    }

    // generate wall for level 1 and level 2
    public void generateWall() {
        // up
        for(int i = 0; i < 32; i++) {
            Wall wall = new Wall(i * 20, 0, "wall");
            walls.add(wall);
        }

        // left
        for(int i = 0; i < 23; i++) {
            Wall wall = new Wall(0, (i + 1) * 20, "wall");
            walls.add(wall);
        }

        // right
        for(int i = 0; i < 23; i++) {
            Wall wall = new Wall(620, (i + 1) * 20, "wall");
            walls.add(wall);
        }
    }

    public void drawObject(Graphics g) {
        if(pop.isLive()) {
            this.drawBigleg(g);
            this.drawBlock(g);
            this.drawKatch(g);
            this.drawPop(g);
            this.drawWall(g);
        }
    }

    // set all the backgrounds
    public void setBackground(Graphics g) {
        BufferedImage img;

        // game level 1
        if(pop.getLevel() == 1) {
            try{
                if(pop.isLive())
                    img = ImageIO.read(new File("resource\\image\\Background1.png"));
                else
                    img = ImageIO.read(new File("resource\\image\\Title1.png"));

                g.drawImage(img, 0, 0, null);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }

        // game level 2
        if(pop.getLevel() == 2) {
            try{
                if(pop.isLive())
                    img = ImageIO.read(new File("resource\\image\\Background2.png"));
                else
                    img = ImageIO.read(new File("resource\\image\\Title2.png"));

                g.drawImage(img, 0, 0, null);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }

        // game fail or success
        if(biglegs2.size() == 0) {
            pop.setLive(false);

            try{
                img = ImageIO.read(new File("resource\\image\\Congratulation.png"));
                g.drawImage(img, 0, 0, null);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    // show scores
    public void showScore(Graphics g) {
        g.setFont(font);
        g.drawString("Score " + pop.getScore(), 25, 420);

        // score of block, level 1
        for(int i = 0; i < blocks1.size(); i++) {
            if(!blocks1.get(i).isLive()) {
                if(blocks1.get(i).getType() == "solidBlock")
                    pop.setScore(pop.getScore() + 10);
                if(blocks1.get(i).getType() == "lifeBlock")
                    katch.setLife(katch.getLife() + 1);
                if(blocks1.get(i).getType() == "purpleBlock")
                    pop.setScore(pop.getScore() + 20);
                if(blocks1.get(i).getType() == "yellowBlock")
                    pop.setScore(pop.getScore() + 30);
                if(blocks1.get(i).getType() == "redBlock")
                    pop.setScore(pop.getScore() + 40);
                if(blocks1.get(i).getType() == "greenBlock")
                    pop.setScore(pop.getScore() + 50);
                if(blocks1.get(i).getType() == "cyanBlock")
                    pop.setScore(pop.getScore() + 60);
                if(blocks1.get(i).getType() == "blueBlock")
                    pop.setScore(pop.getScore() + 70);
                if(blocks1.get(i).getType() == "grayBlock")
                    pop.setScore(pop.getScore() + 80);
                if(blocks1.get(i).getType() == "splitBlock")
                    pop.setScore(pop.getScore() + 100);

                blocks1.remove(i);
            }
        }

        // score of smallBigleg, level 1
        for(int i = 0; i < biglegs1.size(); i++) {
            if(!biglegs1.get(i).isLive()) {
                pop.setScore(pop.getScore() + 200);
                biglegs1.remove(i);
            }
        }

        // score of block, level 2
        for(int i = 0; i < blocks2.size(); i++) {
            if(!blocks2.get(i).isLive()) {
                if(blocks2.get(i).getType() == "solidBlock")
                    pop.setScore(pop.getScore() + 10);
                if(blocks2.get(i).getType() == "lifeBlock")
                    katch.setLife(katch.getLife() + 1);
                if(blocks2.get(i).getType() == "purpleBlock")
                    pop.setScore(pop.getScore() + 20);
                if(blocks2.get(i).getType() == "yellowBlock")
                    pop.setScore(pop.getScore() + 30);
                if(blocks2.get(i).getType() == "redBlock")
                    pop.setScore(pop.getScore() + 40);
                if(blocks2.get(i).getType() == "greenBlock")
                    pop.setScore(pop.getScore() + 50);
                if(blocks2.get(i).getType() == "blueBlock")
                    pop.setScore(pop.getScore() + 60);
                if(blocks2.get(i).getType() == "grayBlock")
                    pop.setScore(pop.getScore() + 70);
                if(blocks2.get(i).getType() == "splitBlock")
                    pop.setScore(pop.getScore() + 100);

                blocks2.remove(i);
            }
        }

        // score of largeBigleg, level 2
        for(int i = 0; i < biglegs2.size(); i++) {
            if(!biglegs2.get(i).isLive()) {
                pop.setScore(pop.getScore() + 400);
                biglegs2.remove(i);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(g);
        this.changeLevel();
        this.resetPop();
        this.drawObject(g);
        this.showScore(g);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT)
            katch.setX(katch.getX() - 15);
        if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT)
            katch.setX(katch.getX() + 15);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}
