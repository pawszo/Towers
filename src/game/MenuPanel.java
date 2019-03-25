package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static game.Window.GAMEPANEL;
import static game.Window.INSTRUCTIONPANEL;

public class MenuPanel extends JPanel implements ActionListener {

    private Window frame;
    private JButton start, exit, highScores, instructions;
    private JLabel logo;
    private GridBagConstraints c;
    private BufferedImage logoImage;


    public MenuPanel(Dimension dim, Window frame) {
        this.frame = frame;
        setPreferredSize(dim);
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        createComponents();
        addComponents();
    }

    private void createComponents() {
        start = new JButton("START");
        start.setPreferredSize(new Dimension(300, 40));
        start.addActionListener(this);
        highScores = new JButton("HIGH SCORES");
        highScores.setPreferredSize(new Dimension(300, 40));
        highScores.addActionListener(this);
        instructions = new JButton("HOW TO PLAY");
        instructions.setPreferredSize(new Dimension(300, 40));
        instructions.addActionListener(this);
        exit = new JButton("EXIT");
        exit.setPreferredSize(new Dimension(300, 40));
        exit.addActionListener(this);


        try {
            logoImage = ImageIO.read(new File("/home/nitropawel/workspace/Towers/res/sprite/logo.png"));
            logo = new JLabel(new ImageIcon(logoImage));
        } catch (IOException e) {
            System.out.println(e);
        }


    }

    private void addComponents() {
        c.fill = GridBagConstraints.SOUTH;
        c.gridx = 0;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridy = 0;
        add(logo, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 100;
        add(start, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 200;
        add(instructions, c);
        /** HIGH SCORES WILL BE ADDED ONCE REMOTE SERVER IS ON
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 300;
        add(highScores, c);
         */
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 400;
        add(exit, c);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start) {

            frame.getCl().show(frame.getMainPanel(), GAMEPANEL);
            frame.createGamePanel();
            System.out.println("START");
        }
        if(e.getSource() == exit) {
            System.exit(0);
        }
        if(e.getSource() == instructions) {
            frame.getCl().show(frame.getMainPanel(), INSTRUCTIONPANEL);
        }

    }
}
