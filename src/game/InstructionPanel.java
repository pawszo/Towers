package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InstructionPanel extends JPanel implements ActionListener {

    private JButton back;
    private BufferedImage instructionImage;
    private JLabel instructionLabel;
    private Window frame;
    private GridBagConstraints c;

    public InstructionPanel(Dimension dim, Window frame) {
        this.frame = frame;
        setPreferredSize(dim);
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        setBackground(Color.BLACK);
        createAndAdd();

    }

    private void createAndAdd() {
        back = new JButton("RETURN");
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(300, 40));
        try {
            instructionImage = ImageIO.read(new File("/home/nitropawel/workspace/Towers/res/sprite/instructions.png"));
            instructionLabel = new JLabel(new ImageIcon(instructionImage));
        } catch (IOException e) {
            System.out.println(e);
        }
        c.fill = GridBagConstraints.SOUTH;
        c.gridx = 0;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridy = 0;
        add(instructionLabel, c);
        c.fill = GridBagConstraints.LAST_LINE_START;
        c.gridy = 700;
        add(back, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            frame.getCl().show(frame.getMainPanel(), Window.MENUPANEL);
            System.out.println("BACK");

        }
    }
}
