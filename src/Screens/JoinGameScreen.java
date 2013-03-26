package screens;

import view.JoinGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGameScreen implements JoinGameView {
    private final MainFrame mainFrame;
    private final JoinGameController controller;
    private final JPanel panel;

    public JoinGameScreen(MainFrame mainFrame, JoinGameController controller) {

        this.mainFrame = mainFrame;
        this.controller = controller;

        Image image = new ImageIcon(".\\Prototypes\\hdwallpapersbase.com.jpeg").getImage();
        panel = mainFrame.createPanel(image);

        JLabel label = new JLabel("Players Joined");
        panel.add(label);
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
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setSize(250, 400);
        playerList.setLocation(100, 130);

        JButton quit = new JButton("Quit");
        panel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(650, 550);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Quit ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void displayConnectedPlayers(String[] playersConnected) {
    }
}
