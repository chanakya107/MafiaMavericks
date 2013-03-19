package UserScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartServerScreen {
    public void startServer(final JFrame frame) {
        frame.setVisible(false);
        final JFrame serverFrame = new JFrame("Mafia");
        serverFrame.setVisible(true);
        serverFrame.setBounds(500, 300, 900, 700);
        JPanel serverPanel = new JPanel();
        serverPanel.setBackground(Color.black);
        serverPanel.setLayout(null);

        JLabel label = new JLabel("Player Joined");
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

        JList playerList = new JList(players);
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
               new WelcomeScreen().display(serverFrame);
            }
        });

        JButton back = new JButton("Back");

        serverPanel.add(back);
        back.setSize(150, 50);
        back.setLocation(600, 500);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverFrame.setVisible(false);
                frame.setVisible(true);
            }
        });
        serverFrame.setContentPane(serverPanel);
        serverFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
