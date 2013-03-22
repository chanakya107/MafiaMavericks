package UserScreens;

import ServerClient.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomeScreen {
    JFrame frame;
    JPanel firstPanel;

    public HomeScreen() {
        frame = new JFrame("Mafia");
        firstPanel = new JPanel();
        frame.setVisible(true);
        firstPanel.setBackground(Color.black);
        firstPanel.setLayout(null);
        frame.setBounds(500, 300, 900, 700);
        frame.add(firstPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void display() {
        JButton startServer = new JButton("Start server");
        firstPanel.add(startServer);
        startServer.setSize(150, 50);
        startServer.setLocation(100, 300);

        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                firstPanel.setVisible(false);
                new StartServerScreen().startServer(frame, firstPanel);

                Server server = Server.createServer(1);
                try {
                    server.startServer();
                    server.start();
                    server.sendMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        JButton joinGame = new JButton("Join Game");
        firstPanel.add(joinGame);
        joinGame.setSize(150, 50);
        joinGame.setLocation(100, 400);

        joinGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPanel.setVisible(false);
                new ClientDetailScreen().joinGame(frame,firstPanel,"localhost");

            }
        });

        JButton quit = new JButton("Quit");
        firstPanel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(100, 500);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you really want to Quit?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

    }
}
