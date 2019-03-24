package game;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {


    public Window() {
        setTitle("Towers v1");
        setSize(1600, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280, 720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}