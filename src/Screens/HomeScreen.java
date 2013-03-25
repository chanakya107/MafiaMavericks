package Screens;

import View.HomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen implements HomeView {
    private JFrame frame;
    private JPanel firstPanel;
    private JButton startServer;
    private JButton joinGame;
    private JButton quit;

    public HomeScreen() {
        frame = new JFrame("Mafia");
        firstPanel = new JPanel();
        frame.setVisible(true);
        firstPanel.setBackground(Color.black);
        firstPanel.setLayout(null);
        frame.setBounds(500, 300, 900, 700);
        frame.add(firstPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        startServer = new JButton("Start server");
        firstPanel.add(startServer);
        startServer.setSize(150, 50);
        startServer.setLocation(100, 300);

        joinGame = new JButton("Join Game");
        firstPanel.add(joinGame);
        joinGame.setSize(150, 50);
        joinGame.setLocation(100, 400);


        quit = new JButton("Quit");
        firstPanel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(100, 500);
    }

    public void display() {
        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                onStart();
            }
        });

        joinGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                onJoinGame();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onQuit();
            }
        });
    }

    @Override
         public void onStart() {
        firstPanel.setVisible(false);
        new StartServerScreen(frame, firstPanel).display();
    }

    @Override
    public void onJoinGame() {
        firstPanel.setVisible(false);
        new ClientDetailsScreen().joinGame(frame, firstPanel);
    }

    @Override
    public void onQuit() {
        int option = JOptionPane.showConfirmDialog(null, "Do you really want to Quit?", "", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
