package Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen {
    private JFrame frame;
    private JPanel homePanel;
    private JButton startServer;
    private JButton joinGame;
    private JButton quit;

    public HomeScreen() {


        frame = new JFrame("Mafia");
        homePanel = new JPanel();
        frame.setVisible(true);
        homePanel.setBackground(Color.black);
        homePanel.setLayout(null);
        frame.setBounds(500, 300, 900, 700);
        frame.add(homePanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        PanelImage panelImage = new PanelImage(new ImageIcon("D:\\TestCodeForProjectUsage\\Tulips.jpg").getImage());
        panelImage.setVisible(true);

        startServer = new JButton("Start server");

        panelImage.add(startServer);
        startServer.setSize(150, 50);
        startServer.setLocation(100, 300);

        joinGame = new JButton("Join Game");

        panelImage.add(joinGame);
        joinGame.setSize(150, 50);
        joinGame.setLocation(100, 400);


        quit = new JButton("Quit");

        panelImage.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(100, 500);
    }

    public void display() {
        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                homePanel.setVisible(false);
                new StartServerScreen(frame, homePanel).display();
            }
        });

        joinGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePanel.setVisible(false);
                new ClientDetailsScreen(frame, homePanel).joinGame();
            }
        });

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
