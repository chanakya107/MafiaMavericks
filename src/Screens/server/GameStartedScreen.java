package screens.server;

import controllers.server.GameStartedController;
import controllers.server.Player;
import screens.controls.MainFrame;
import view.server.GameStartedView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameStartedScreen implements GameStartedView {

    private final MainFrame mainFrame;
    private GameStartedController controller;
    private final JPanel panel;
    private final JButton quit;
    private final DefaultListModel<String> playersDefaultList;
    private final JList<String> playerList;
    private final JLabel label;
    private final JLabel gameStartedLabel;

    public GameStartedScreen(MainFrame mainFrame, final GameStartedController controller) {

        this.mainFrame = mainFrame;
        this.controller = controller;
        controller.bind(this);

        Image image = new ImageIcon(".\\Images\\www.desktopwallpapers4.me.jpg").getImage();
        panel = mainFrame.createPanel(image);

        quit = new JButton("Quit");
        panel.add(quit);
        quit.setBounds(1120, 520, 150, 50);

        gameStartedLabel = new JLabel("Game Started .... ");
        panel.add(gameStartedLabel);
        gameStartedLabel.setFont(new Font("Chiller", Font.PLAIN, 50));
        gameStartedLabel.setForeground(Color.WHITE);
        gameStartedLabel.setBounds(1050, 350, 250, 150);

        label = new JLabel("Player Roles");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setBounds(95, 100, 250, 150);

        playersDefaultList = new DefaultListModel<String>();

        playerList = new JList<String>(playersDefaultList);
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setBounds(60, 200, 250, 450);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Quit ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.stopServer();
                    System.exit(0);
                }
            }
        });

    }

    @Override
    public void displayPlayers(List<Player> players) {
        playersDefaultList.removeAllElements();
        for (Player player : players) {
            playersDefaultList.addElement(player.getName() + " - " + player.getRole());
        }
    }
}
