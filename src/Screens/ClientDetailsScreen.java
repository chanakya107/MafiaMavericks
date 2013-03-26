package screens;

import gameController.Client;
import view.ClientDetailsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientDetailsScreen implements ClientDetailsView {
    private JPanel joinPanel;
    private JTextField serverNameField;
    private JButton connect;
    private JButton cancel;
    private JFrame frame;
    private final JPanel homePanel;

    public ClientDetailsScreen(JFrame frame, JPanel homePanel) {
        this.frame = frame;
        this.homePanel = homePanel;

        joinPanel = new JPanel();
        frame.add(joinPanel);
        joinPanel.setBackground(Color.black);
        joinPanel.setLayout(null);

        JLabel enterServerName = new JLabel("Enter the Server Name :");
        joinPanel.add(enterServerName);
        enterServerName.setSize(300, 150);
        enterServerName.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        enterServerName.setLocation(100, 100);

        serverNameField = new JTextField("");
        joinPanel.add(serverNameField);
        serverNameField.setSize(200, 30);
        serverNameField.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        serverNameField.setLocation(400, 160);

        JLabel enterUserName = new JLabel("Enter the User Name :");
        joinPanel.add(enterUserName);
        enterUserName.setSize(300, 150);
        enterUserName.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        enterUserName.setLocation(100, 200);

        JTextField userNameField = new JTextField("player");
        joinPanel.add(userNameField);
        userNameField.setSize(200, 30);
        userNameField.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        userNameField.setLocation(400, 260);

        connect = new JButton("Connect");
        joinPanel.add(connect);
        connect.setSize(150, 50);
        connect.setLocation(400, 500);

        cancel = new JButton("Cancel");
        joinPanel.add(cancel);
        cancel.setSize(150, 50);
        cancel.setLocation(600, 500);
    }

    public void joinGame() {
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinPanel.setVisible(false);
                boolean connectionStatus;
                connectionStatus = new Client().createClient(serverNameField.getText());
                if (!connectionStatus) {
                    joinPanel.setVisible(true);
                    JOptionPane.showMessageDialog(null, serverNameField.getText() + " server not found");
                } else {
                    new JoinGameScreen().display(frame);
                    JOptionPane.showMessageDialog(null, "Connected to " + serverNameField.getText());
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Cancel ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    joinPanel.setVisible(false);
                    homePanel.setVisible(true);
                }
            }
        });
    }
}

