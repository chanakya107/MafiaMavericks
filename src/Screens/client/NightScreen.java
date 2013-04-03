package screens.client;

import controllers.client.NightController;
import controllers.server.Player;
import screens.controls.MainFrame;
import view.client.NightView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NightScreen implements NightView {
    private final MainFrame mainFrame;
    private final NightController controller;
    private final JPanel panel;
    private final DefaultListModel<String> players;
    private final JList<String> playerList;
    private final JLabel label;

    public NightScreen(MainFrame mainFrame, NightController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        controller.bind(this);

        Image image = new ImageIcon(".\\Images\\nice-cool-pics.com.jpg").getImage();
        panel = mainFrame.createPanel(image);

        label = new JLabel("Mafias");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setBounds(130, 25, 250, 150);

        players = new DefaultListModel<String>();

        playerList = new JList<String>(players);
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setBounds(100, 130, 250, 400);
    }

    @Override
    public void displayMafia(List<Player> mafiaList) {
        players.removeAllElements();
        for (Player mafia : mafiaList) {
            players.addElement(mafia.getName());
        }
    }
}
