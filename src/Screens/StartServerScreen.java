package Screens;

import Channels.Server.SocketServer;
import GameController.Server;
import View.StartServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartServerScreen implements StartServerView {

    private JPanel serverPanel;
    private JButton startGame;
    private JButton cancel;
    private JFrame frame;
    private JPanel firstPanel;

    public StartServerScreen(JFrame frame, final JPanel firstPanel) {
        this.frame = frame;
        this.firstPanel = firstPanel;
        serverPanel = new JPanel();
        frame.add(serverPanel);
        serverPanel.setBackground(Color.black);
        serverPanel.setLayout(null);

        JLabel label = new JLabel("Players Joined");
        serverPanel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setSize(250, 150);
        label.setLocation(130, 25);

        DefaultListModel<String> players = new DefaultListModel<String>();
        players.addElement("Chanakya");
        players.addElement("Deepthi");
        players.addElement("Chethan");
        players.addElement("Raghavendra");

        JList<String> playerList = new JList<String>(players);
        serverPanel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setSize(250, 400);
        playerList.setLocation(100, 130);

        startGame = new JButton("Start Game");
        serverPanel.add(startGame);
        startGame.setSize(150, 50);
        startGame.setLocation(600, 400);

        cancel = new JButton("Cancel");
        serverPanel.add(cancel);
        cancel.setSize(150, 50);
        cancel.setLocation(600, 500);
    }

    public void display() {
        final SocketServer server = new Server().startServer();

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStartGame();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel(server);
            }
        });
    }

    @Override
    public void onCancel(SocketServer server) {
        int option = JOptionPane.showConfirmDialog(null, "Do you really want to Cancel ?", "", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            server.stop();
            serverPanel.setVisible(false);
            firstPanel.setVisible(true);
        }
    }

    @Override
    public void onStartGame() {
        serverPanel.setVisible(false);
        new WelcomeScreen().display(frame);
    }
}
