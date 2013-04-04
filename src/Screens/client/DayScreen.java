package screens.client;

import controllers.client.DayController;
import screens.controls.MainFrame;
import view.client.DayView;

import javax.swing.*;
import java.awt.*;

public class DayScreen implements DayView {
    private final MainFrame mainFrame;
    private final DayController controller;
    private JPanel panel;

    public DayScreen(MainFrame mainFrame, DayController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        Image image = new ImageIcon(".\\Images\\hdwallpapersbase.com.jpg").getImage();
        panel = mainFrame.createPanel(image);

        JLabel label = new JLabel("Day Arrived");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setSize(250, 150);
        label.setLocation(130, 25);
    }

    @Override
    public void displayKilledPlayer() {
        JLabel label = new JLabel("Killed Player : " + controller.getKilledPlayer());
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.RED);
        label.setSize(500, 300);
        label.setLocation(130, 25);
    }

    @Override
    public void displayVoting() {
    }

}