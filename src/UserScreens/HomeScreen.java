package UserScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            public void actionPerformed(ActionEvent e) {
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
                new JoinGameScreen().joinGame(frame);
            }
        });

        JButton quit = new JButton("Quit");
        firstPanel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(100, 500);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Quit ?","",JOptionPane.YES_NO_OPTION);
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
