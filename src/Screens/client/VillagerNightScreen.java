package screens.client;

import controllers.client.VillagerNightController;
import screens.controls.MainFrame;
import view.client.VillagerNightView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VillagerNightScreen implements VillagerNightView {
    private final VillagerNightController controller;
    private final JPanel panel;
    private JLabel label;

    public VillagerNightScreen(MainFrame mainFrame, final VillagerNightController controller) {
        this.controller = controller;

        controller.bind(this);

        panel = mainFrame.createPanel("www.desktopwallpapers4.me.jpg");


        JLabel nameLabel = new JLabel(controller.getCurrentPlayer().getName() + " - " + controller.getCurrentPlayer().getRole());
        panel.add(nameLabel);
        nameLabel.setFont(new Font("Chiller", Font.PLAIN, 50));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(500,2, 500, 250);

    }

    @Override
    public void display() {
        label = new JLabel("Night Arrived");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.red);
        label.setBounds(130, 25, 250, 150);
    }

    @Override
    public String getSelectedPlayer() {
        return null;
    }

    @Override
    public void disableConfirm() {
    }

    @Override
    public void displayLog(List<String> log) {

    }
}
