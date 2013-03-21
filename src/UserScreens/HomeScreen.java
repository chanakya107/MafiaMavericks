package UserScreens;

import ServerClient.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomeScreen {
    public void display() {
        final JFrame frame = new JFrame("Mafia");
        frame.setVisible(true);
        final JPanel firstPanel = new JPanel();
        firstPanel.setBackground(Color.black);
        firstPanel.setLayout(null);

        JButton startServer = new JButton("Start server");
        firstPanel.add(startServer);
        startServer.setSize(150, 50);
        startServer.setLocation(100, 300);

        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new StartServerScreen().startServer(frame);
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
                new JoinGameScreen().joinGame(frame);
                Client client = null;
                try {
                    client = Client.createClient(serverName, 1254);
                    client.getMessage();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        if (client != null) {
                            client.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
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
        frame.setContentPane(firstPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
