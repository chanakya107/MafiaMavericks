package screens.client;

import controllers.client.VillagerNightController;
import controllers.server.Player;
import screens.controls.MainFrame;
import view.client.VillagerNightView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VillagerNightScreen implements VillagerNightView {
    private final VillagerNightController controller;
    private final JPanel panel;
    private final JButton disconnect;
    private JLabel label;

    public VillagerNightScreen(MainFrame mainFrame, final VillagerNightController controller) {
        this.controller = controller;

        controller.bind(this);

        panel = mainFrame.createPanel("www.desktopwallpapers4.me.jpg");

        disconnect = new JButton("Disconnect");
        panel.add(disconnect);
        disconnect.setSize(150, 50);
        disconnect.setLocation(950, 550);

        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you really want to Disconnect ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.disconnectingFromServer();
                }
            }
        });
    }

    @Override
    public void display() {
        label = new JLabel("Night Arrived");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setBounds(130, 25, 250, 150);

    }

    @Override
    public void displayTimer(int count) {
    }

    @Override
    public Player getSelectedPlayer(List<Player> players) {
        return null;
    }
}
