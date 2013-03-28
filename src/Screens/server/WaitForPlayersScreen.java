package screens.server;

import controllers.server.Player;
import controllers.server.WaitForPlayersController;
import screens.controls.MainFrame;
import view.server.WaitForPlayersView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        label.setSize(250, 150);
        label.setLocation(130, 25);

        players = new DefaultListModel<String>();

        playerList = new JList<String>(players);
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setSize(250, 400);
        playerList.setLocation(100, 130);

        startGame = new JButton("Start Game");
        panel.add(startGame);
        startGame.setSize(150, 50);
        startGame.setLocation(600, 400);

        stopServer = new JButton("Stop server");
        panel.add(stopServer);
        stopServer.setSize(150, 50);
        stopServer.setLocation(600, 500);

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
    public void updatePlayers(ArrayList<Player> playerList) {
        players.removeAllElements();
        for (Player player : playerList) {
            players.addElement(player.getName());
        }
    }

}
