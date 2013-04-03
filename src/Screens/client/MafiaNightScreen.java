package screens.client;

import controllers.client.MafiaNightController;
import controllers.server.Player;
import screens.controls.MainFrame;
import view.client.MafiaNightView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MafiaNightScreen implements MafiaNightView {
    private final MainFrame mainFrame;
    private final MafiaNightController controller;
    private final JPanel panel;
    private final JButton disconnect;
    private final JList<String> playerList;
    private final JLabel label;
    private final DefaultListModel<String> players;

    public MafiaNightScreen(MainFrame mainFrame, final MafiaNightController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        controller.bind(this);

        Image image = new ImageIcon(".\\Images\\if.invisionfree.com.jpg").getImage();
        panel = mainFrame.createPanel(image);

        disconnect = new JButton("Disconnect");
        panel.add(disconnect);
        disconnect.setSize(150, 50);
        disconnect.setLocation(950, 550);

        label = new JLabel("Mafias");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setBounds(130, 25, 250, 150);

        players = new DefaultListModel<String>();

        playerList = new JList<String>(players);
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setBounds(100, 130, 250, 400);

        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Disconnect ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.disconnectingFromServer();
                }
            }
        });
    }

    @Override
    public void serverDisconnected(String serverName) {
        JOptionPane.showConfirmDialog(null, "Connection to server : " + serverName + " is lost", "", JOptionPane.DEFAULT_OPTION);
        controller.goToHome();
    }

    @Override
    public void displayAtNight(List<Player> mafiaList) {
        players.removeAllElements();
        for (Player mafia : mafiaList) {
            players.addElement(mafia.getName());
        }
    }
}


