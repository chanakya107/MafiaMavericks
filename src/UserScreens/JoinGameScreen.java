package UserScreens;

import ServerClient.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class JoinGameScreen {
    public void joinGame(final JFrame frame, final JPanel firstPanel, String serverName) {
        final JPanel joinPanel = new JPanel();
        frame.add(joinPanel);
        joinPanel.setBackground(Color.black);
        joinPanel.setLayout(null);


        JButton cancel = new JButton("Cancel");

        joinPanel.add(cancel);
        cancel.setSize(150, 50);
        cancel.setLocation(600, 500);
        connectTo(serverName);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Cancel ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    joinPanel.setVisible(false);
                    firstPanel.setVisible(true);
                }
            }
        });
    }
    public void connectTo(String serverName) {
        Client client = null;
        try {
            client = Client.createClient(serverName, 1254);
            client.start();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
