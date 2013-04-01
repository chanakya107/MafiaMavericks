package screens.client;

import controllers.client.NightController;
import screens.controls.MainFrame;
import view.client.NightView;

import javax.swing.*;
import java.awt.*;

public class NightScreen implements NightView {
    private final MainFrame mainFrame;
    private final NightController controller;
    private final JPanel panel;

    public NightScreen(MainFrame mainFrame, NightController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        Image image = new ImageIcon(".\\Images\\nice-cool-pics.com.jpg").getImage();
        panel = mainFrame.createPanel(image);
    }
}
