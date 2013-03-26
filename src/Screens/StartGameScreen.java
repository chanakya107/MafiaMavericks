package screens;

import channels.server.SocketServer;
import gameController.Server;
import view.StartGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameScreen implements StartGameView {


    private final MainFrame mainFrame;
    private final StartGameController controller;
    private final JPanel panel;
    private final JButton startGame;
    private final JButton cancel;

    public StartGameScreen(MainFrame mainFrame,StartGameController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        controller.bind(this);

        panel = mainFrame.createPanel();

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

        startGame = new JButton("Start Game");
        panel.add(startGame);
        startGame.setSize(150, 50);
        startGame.setLocation(600, 400);

        cancel= new JButton("Cancel");
        panel.add(cancel);
        cancel.setSize(150, 50);
        cancel.setLocation(600, 500);

        addButtonHandlers();
    }

    private void addButtonHandlers() {

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startGame();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you really want to Cancel ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.stopServer();
                }
            }
        });
    }

}
