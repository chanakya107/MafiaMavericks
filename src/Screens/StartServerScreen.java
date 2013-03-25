package Screens;

import Channels.Server.SocketServer;
import Channels.Server.SocketServerListener;
import Channels.SocketChannel;
import View.StartServerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartServerScreen implements SocketServerListener, StartServerView {

    public void startServer(final JFrame frame, final JPanel firstPanel) {
        final JPanel serverPanel = new JPanel();
        frame.add(serverPanel);
        serverPanel.setBackground(Color.black);
        serverPanel.setLayout(null);

        final SocketServer server = new SocketServer(1254, this);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        JButton startGame = new JButton("Start Game");

        serverPanel.add(startGame);
        startGame.setSize(150, 50);
        startGame.setLocation(600, 400);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverPanel.setVisible(false);
                new WelcomeScreen().display(frame);
            }
        });

        JButton cancel = new JButton("Cancel");
        serverPanel.add(cancel);
        cancel.setSize(150, 50);
        cancel.setLocation(600, 500);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you really want to Cancel ?", "", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    server.stop();
                    serverPanel.setVisible(false);
                    firstPanel.setVisible(true);
                }
            }
        });
    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {

    }
}
