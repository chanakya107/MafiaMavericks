package screens.server;

import controllers.server.Player;
import controllers.server.WaitForPlayersController;
import screens.controls.MainFrame;
import view.server.WaitForPlayersView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WaitForPlayersScreen implements WaitForPlayersView {

    private final MainFrame mainFrame;
    private final WaitForPlayersController controller;
    private final JPanel panel;
    private final JButton startGame;
    private final JButton stopServer;
    private final JLabel label;
    private JList<String> playerList;
    private DefaultListModel<String> players;

    public WaitForPlayersScreen(MainFrame mainFrame, WaitForPlayersController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        controller.bind(this);

        Image image = new ImageIcon(".\\Prototypes\\hdwallpapersbase.com.jpeg").getImage();
        panel = mainFrame.createPanel(image);

        label = new JLabel("Players Joined");
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

        startGame = new JButton("Start Game");
        panel.add(startGame);
        startGame.setBounds(600, 400, 150, 50);

        stopServer = new JButton("Stop server");
        panel.add(stopServer);
        stopServer.setBounds(600, 500, 150, 50);

        addButtonHandlers();
    }

    private void addButtonHandlers() {

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startGame();
            }
        });

        stopServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you really want to Cancel ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.stopServer();
                }
            }
        });
    }

    @Override
    public void updatePlayers(List<Player> playerList) {
        players.removeAllElements();
        for (Player player : playerList) {
            players.addElement(player.getName());
        }
    }

}
