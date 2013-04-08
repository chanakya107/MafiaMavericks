package screens.client;

import controllers.client.PlayerKilledController;
import controllers.server.Player;
import screens.controls.MainFrame;
import view.client.PlayerKilledView;

import javax.swing.*;
import java.awt.*;

public class PlayerKilledScreen implements PlayerKilledView {
    private final MainFrame mainFrame;
    private final PlayerKilledController controller;
    JPanel panel;
    private JLabel label;

    public PlayerKilledScreen(MainFrame mainFrame, PlayerKilledController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        controller.bind(this);

        panel = mainFrame.createPanel("scottystarnes.wordpress.com.jpg");
    }

    @Override
    public void displayKilledPlayer(Player killedPlayer) {
        label = new JLabel(killedPlayer.getName() + " is Killed");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 90));
        label.setForeground(Color.BLACK);
        label.setBounds(130, 25, 500, 250);
    }
}
