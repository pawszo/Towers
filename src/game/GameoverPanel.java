package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameoverPanel extends JPanel implements ActionListener {

    private Window frame;
    private GridBagConstraints c;
    private JLabel gameoverLabel, scoreLabel;
    private JButton goToMain;
    private BufferedImage image;

    public GameoverPanel(Dimension dim, Window frame) {
        this.frame = frame;
        setPreferredSize(dim);
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        createAndAdd();
    }

    private void createAndAdd() {
        goToMain = new JButton("RETURN TO MAIN MENU");
        goToMain.addActionListener(this);
        goToMain.setPreferredSize(new Dimension(300, 40));
        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 36));
        try {
            image = ImageIO.read(new File("/home/nitropawel/workspace/Towers/res/sprite/gameover.png"));
            gameoverLabel = new JLabel(new ImageIcon(image));
        } catch (IOException e) {
            System.out.println(e);
        }
        c.fill = GridBagConstraints.SOUTH;
        c.gridx = 0;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridy = 0;
        add(gameoverLabel, c);
        c.fill = GridBagConstraints.CENTER;
        c.gridy = 500;
        add(scoreLabel, c);
        c.fill = GridBagConstraints.LAST_LINE_START;
        c.gridy = 700;
        add(goToMain, c);

    }
    public void setScore(int score) {
        scoreLabel.setText("" + score);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goToMain) {
            frame.getCl().show(frame.getMainPanel(), Window.MENUPANEL);
        }
    }
}
