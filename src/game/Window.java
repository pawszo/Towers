package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {

    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private JPanel mainPanel;
    private CardLayout cl;
    private Dimension dim;
    private InstructionPanel instructionPanel;
    private GameoverPanel gameoverPanel;

    public static String GAMEPANEL = "GAMEPANEL";
    public static String MENUPANEL = "MENUPANEL";
    public static String INSTRUCTIONPANEL = "INSTRUCTIONPANEL";
    public static String GAMEOVERPANEL = "GAMEOVERPANEL";

    public Window() {
        dim = new Dimension(1280, 720);
        setTitle("Towers v1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        menuPanel = new MenuPanel(dim,this);
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(menuPanel, MENUPANEL);
        gameoverPanel = new GameoverPanel(dim, this);
        mainPanel.add(gameoverPanel, GAMEOVERPANEL);
        instructionPanel = new InstructionPanel(dim, this);
        mainPanel.add(instructionPanel, INSTRUCTIONPANEL);
        cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, MENUPANEL);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void createGamePanel() {
        gamePanel = new GamePanel(dim, this);
        mainPanel.add(gamePanel, GAMEPANEL);
        cl.show(mainPanel, GAMEPANEL);
        gamePanel.requestFocus();
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    public GameoverPanel getGameoverPanel() {
        return this.gameoverPanel;
    }



    public CardLayout getCl() {
        return cl;
    }
    public void setCl(CardLayout cl) {
        this.cl = cl;
    }
    public JPanel getMainPanel() {
        return this.mainPanel;
    }

}