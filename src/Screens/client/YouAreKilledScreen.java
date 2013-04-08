package screens.client;

import controllers.client.YouAreKilledController;
import screens.controls.MainFrame;
import view.client.YouAreKilledView;

import javax.swing.*;
import java.awt.*;

public class YouAreKilledScreen implements YouAreKilledView {
    private final MainFrame mainFrame;
    private final YouAreKilledController controller;
    private final JPanel panel;
    private final JLabel label;

    public YouAreKilledScreen(MainFrame mainFrame, YouAreKilledController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createPanel("thaiintelligentnews.wordpress.com1.jpg");

        label = new JLabel("You are Killed");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 90));
        label.setForeground(Color.WHITE);
        label.setBounds(130, 25, 500, 250);
    }
}
