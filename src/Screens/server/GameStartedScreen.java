package screens.server;

import controllers.GameLog;
import controllers.client.Client;
import controllers.server.GameStartedController;
import screens.controls.MainFrame;
import view.server.GameStartedView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameStartedScreen implements GameStartedView {

    private final JPanel panel;
    private final JButton quit;
    private final DefaultListModel<String> playersDefaultList;
    private final JList<String> playerList;
    private final JLabel label;
    private final JLabel log;
    private GameStartedController controller;
    private JLabel gameStartedLabel;

    public GameStartedScreen(MainFrame mainFrame, final GameStartedController controller) {

        this.controller = controller;
        controller.bind(this);

        panel = mainFrame.createPanel("www.desktopwallpapers4.me.jpg");

        quit = new JButton("Quit");
        panel.add(quit);
        quit.setBounds(300, 600, 150, 50);

        label = new JLabel("Player Roles");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setBounds(80, 10, 250, 150);

        log = new JLabel("Game Log");
        panel.add(log);
        log.setFont(new Font("Chiller", Font.PLAIN, 50));
        log.setForeground(Color.WHITE);
        log.setBounds(400, 10, 250, 150);

        playersDefaultList = new DefaultListModel<String>();

        playerList = new JList<String>(playersDefaultList);
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setBounds(60, 110, 250, 450);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Quit ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.stopServer();
                }
            }
        });
    }

    @Override
    public void displayPlayers(List<Client> players) {
        playersDefaultList.removeAllElements();
        for (Client client : players) {
            playersDefaultList.addElement(client.getPlayer().getName() + " - " + client.getPlayer().getRole());
        }
    }

    @Override
    public void displayLog() {
        final DefaultListModel<String> logDefaultList = new DefaultListModel<String>();
        final JList<String> logList = new JList<String>(logDefaultList);
        JScrollPane scrollPane = new JScrollPane(logList);
        panel.add(scrollPane);
        logList.setBackground(Color.GRAY);
        logList.setForeground(Color.WHITE);
        logList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        scrollPane.setBounds(370, 110, 350, 450);

        logDefaultList.removeAllElements();
        for (String log : GameLog.getLog()) {
            logDefaultList.addElement(log);
        }
    }
}


