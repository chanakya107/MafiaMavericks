package UserScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                if (serverName.equals(""))
                    firstPanel.setVisible(true);
                else {
                    String playerName = JOptionPane.showInputDialog(null, "Enter your Name", "", JOptionPane.QUESTION_MESSAGE);
                    if (playerName.equals(""))
                        firstPanel.setVisible(true);
                    else {
                        firstPanel.setVisible(false);
                        new JoinGameScreen().joinGame(frame, firstPanel, serverName);
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

    }
}
