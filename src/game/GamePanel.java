package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import util.KeyHandler;
import util.MouseHandler;
import states.GameStateManager;

//import .

public class GamePanel extends JPanel implements Runnable {

    public static int width;
    public static int height;

    private Thread thread;
    private BufferedImage image;
    private Graphics2D g;
    private boolean running = false;
    private MouseHandler mouse;
    private KeyHandler key;
    private Window frame;
    private Image img;



    private GameStateManager gsm;

    public GamePanel() {
        super();
    }
    public GamePanel(Dimension dim, Window frame) {
        this.frame = frame;
        try {
            img = ImageIO.read(new File("/home/nitropawel/workspace/Towers/res/sprite/background.png"));
        } catch(IOException e) {}
        this.width = (int) dim.getWidth();
        this.height = (int) dim.getHeight();
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }




    public void init() {
        running = true;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) image.getGraphics();

        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gsm = new GameStateManager(frame);
    }

    public void run() {
        init();

        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ;
        /** time_before_update */

        final int MUBR = 5;
        /** must_update_before_render */

        final double TARGET_FPS = 60;
        final double TTBR = 100000000 / TARGET_FPS; /** Total_time_before_render */

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        while (running) {

            double now = System.nanoTime();
            int updateCount = 0;

            while (((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                update();
                input(mouse, key);
                lastUpdateTime += TBU;
                updateCount++;
            }

            if (now - lastUpdateTime > TBU) {
                lastUpdateTime = now = TBU;
            }
            input(mouse, key);
            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    //    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (((now - lastRenderTime) < TTBR) && ((now - lastUpdateTime) < TBU)) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("ERROR: yielding thread");
                }

                now = System.nanoTime();
            }
        }
    }

    public void stopRender() {
        thread.suspend();
    }

    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void update() {
        gsm.update();
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        gsm.input(mouse, key);
    }

    public void render() {
        if (g != null) {
            paintComponent(g);
            gsm.render(g);
        }

    }

    public void draw() {
        Graphics g2 = (Graphics2D) this.getGraphics();
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();


    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);

    }

}
