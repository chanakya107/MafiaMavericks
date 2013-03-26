package screens;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private final JFrame frame;

    public MainFrame() {
        frame = new JFrame("Mafia");
        frame.setVisible(true);
        frame.setBackground(Color.black);
        frame.setBounds(500, 300, 900, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JPanel createPanel() {
        frame.getContentPane().removeAll();
        PanelImage panel = new PanelImage(new ImageIcon(".\\Prototypes\\nice-cool-pics.com.jpeg").getImage());
        panel.setLayout(null);
        //panel.setBackground(Color.BLACK);
        frame.getContentPane().add(panel);
        frame.pack();

        return panel;
    }
}
