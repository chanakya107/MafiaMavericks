package screens.controls;

import screens.controls.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private final JFrame frame;

    public MainFrame() {
        frame = new JFrame("Mafia");
        frame.setVisible(true);
        frame.setBackground(Color.black);
        frame.setBounds(new Rectangle(1080,1920));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(300,0);
    }

    public JPanel createPanel(Image image) {
        frame.getContentPane().removeAll();
        ImagePanel panel = new ImagePanel(image);
        panel.setLayout(null);
        frame.getContentPane().add(panel);
        frame.pack();

        return panel;
    }
}
