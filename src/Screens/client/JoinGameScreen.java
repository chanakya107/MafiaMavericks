package screens.client;

import controllers.client.JoinGameController;
import screens.controls.MainFrame;
import view.client.JoinGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGameScreen implements JoinGameView {
    private final JoinGameController controller;
    private final JPanel panel;
    private final JLabel label;
    private final JButton disconnect;
    private JLabel connectedToServer;
    private DefaultListModel<String> players;
    private JList<String> playerList;


    public JoinGameScreen(MainFrame mainFrame, final JoinGameController controller) {

        this.controller = controller;

        panel = mainFrame.createPanel("hdwallpapersbase.com.jpg");

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

        disconnect = new JButton("Disconnect");
        panel.add(disconnect);
        disconnect.setSize(150, 50);
        disconnect.setLocation(650, 550);

        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Disconnect ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.disconnectingFromServer();
                }
            }
        });
    }

    @Override
    public void displayPlayers(String[] playersConnected) {
        players.removeAllElements();
        for (String player : playersConnected) {
            players.addElement(player);
        }
    }

    @Override
    public void connectedToServer(String serverName, String playerName) {
        String connectedMessage = "Connected To " + serverName + " as " + playerName;
        connectedToServer = new JLabel(connectedMessage);
        panel.add(connectedToServer);
        connectedToServer.setFont(new Font("Chiller", Font.PLAIN, 30));
        connectedToServer.setForeground(Color.red);
        connectedToServer.setSize(350, 100);
        connectedToServer.setLocation(400, 30);

    }

    @Override
    public void serverDisconnected(String message) {
        controller.serverDisconnected(message);
    }
}
