package SuperRainbowReef;

import javax.swing.*;

public class Game extends JFrame {

    private Panel panel;

    public void start() {
        panel = new Panel();
        Thread thread = new Thread(panel);
        thread.start();
        this.add(panel);
        this.addKeyListener(panel);
        this.setTitle("Super Rainbow Reef");
        this.setSize(640, 480);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String [] args) {
        new Game().start();
    }
}
