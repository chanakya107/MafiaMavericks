package screens.controls;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame {
    private final JFrame frame;

    public MainFrame() {
        frame = new JFrame("Mafia");
        frame.setVisible(true);
        frame.setBackground(Color.black);
        frame.setBounds(new Rectangle(1080, 1920));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(300, 0);
    }

    public JPanel createPanel(String imageFileName) {
        URL url = getClass().getClassLoader().getResource("Images/" + imageFileName);
        Image image = (new ImageIcon(url)).getImage();
        frame.getContentPane().removeAll();
        ImagePanel panel = new ImagePanel(image);
        panel.setLayout(null);
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        frame.pack();
        return panel;
    }
}

