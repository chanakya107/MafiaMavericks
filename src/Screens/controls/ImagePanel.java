package screens.controls;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(800, 600);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}



