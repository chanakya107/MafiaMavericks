package screens;

import javax.swing.*;
import java.awt.*;

public class PanelImage extends JPanel {

    private Image img;

    public PanelImage(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
      setPreferredSize(size);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);

    }


}



