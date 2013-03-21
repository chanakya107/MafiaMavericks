package UserScreens;

import ServerClient.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomeScreen {
    JFrame frame = new JFrame("Mafia");

    public void display() {
        final JPanel firstPanel = new JPanel();
        frame.setVisible(true);
        firstPanel.setBackground(Color.black);
        firstPanel.setLayout(null);

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
                    server.start();
                    server.listen();
                    server.sendMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        server.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
                String serverName = JOptionPane.showInputDialog(null, "Enter the server Name", "", JOptionPane.QUESTION_MESSAGE);
                String playerName = JOptionPane.showInputDialog(null, "Enter your Name", "", JOptionPane.QUESTION_MESSAGE);
                firstPanel.setVisible(false);
                JoinGameScreen joinGameScreen =  new JoinGameScreen();
                joinGameScreen.joinGame(frame, firstPanel);
                joinGameScreen.connectTo(serverName);
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

        frame.setBounds(500, 300, 900, 700);
        frame.add(firstPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
