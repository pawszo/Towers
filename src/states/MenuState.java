package states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import util.KeyHandler;
import util.MouseHandler;

import javax.swing.*;

public class MenuState extends GameState {

    private JMenu menu;
    private JMenuBar menuBar;
    private JMenuItem menuItem;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_BACK_SPACE);
        menu.getAccessibleContext().setAccessibleDescription("File menu");
        menuBar.add(menu);
        menuItem = new JMenuItem("new", new ImageIcon("/home/nitropawel/Programs/Towers/res/sprite/menuitem.png"));
        menuItem.setMnemonic(KeyEvent.VK_E);
        menu.add(menuItem);
        menu.addSeparator();
        menu.setVisible(true);
        menuBar.setVisible(true);
        menuItem.setVisible(true);

    }

    @Override
    public void update() {
        menu.updateUI();

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(Graphics2D g) {
        menuBar.update(g);
        menuBar.paint(g);
        menu.update(g);
        menu.paint(g);
        menuItem.update(g);
        menuItem.paint(g);


    }

    @Override
    public void printInstructions(Graphics2D g) {
        // TODO Auto-generated method stub

    }

}