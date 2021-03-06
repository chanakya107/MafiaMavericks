package screens.client;

import controllers.client.ClientDetailsController;
import screens.controls.MainFrame;
import view.client.ClientDetailsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientDetailsScreen implements ClientDetailsView {

    private ClientDetailsController controller;
    private JPanel panel;
    private JLabel enterServerName;
    private JTextField serverNameField;
    private JLabel enterUserName;
    private JButton connect;
    private JButton cancel;
    private JTextField userNameField;
    private JLabel statusLabel;


    public ClientDetailsScreen(MainFrame mainFrame, ClientDetailsController controller) {

        this.controller = controller;
        statusLabel = new JLabel();

        panel = mainFrame.createPanel("hdwallpapersbase.com.jpg");

        enterServerName = new JLabel("Enter the server Name :");
        enterServerName.setForeground(Color.WHITE);
        panel.add(enterServerName);

        enterServerName.setSize(300, 150);
        enterServerName.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        enterServerName.setLocation(100, 100);

        serverNameField = new JTextField("localhost");
        panel.add(serverNameField);
        serverNameField.setSize(200, 30);
        serverNameField.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        serverNameField.setLocation(400, 160);

        enterUserName = new JLabel("Enter the User Name :");
        panel.add(enterUserName);
        enterUserName.setForeground(Color.WHITE);
        enterUserName.setSize(300, 150);
        enterUserName.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        enterUserName.setLocation(100, 200);

        userNameField = new JTextField("player");
        panel.add(userNameField);
        userNameField.setSize(200, 30);
        userNameField.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        userNameField.setLocation(400, 260);

        connect = new JButton("Connect");
        panel.add(connect);
        connect.setSize(150, 50);
        connect.setLocation(200, 400);


        cancel = new JButton("Cancel");
        panel.add(cancel);
        cancel.setSize(150, 50);
        cancel.setLocation(400, 400);

        addButtonHandlers();
    }

    @Override
    public String getServerName() {
        return serverNameField.getText();
    }

    @Override
    public String getPlayerName() {
        return userNameField.getText();
    }

    @Override
    public void display(String message) {
        panel.remove(statusLabel);
        statusLabel = new JLabel(message);
        panel.add(statusLabel);
        statusLabel.setBounds(200, 280, 1000, 150);
        statusLabel.setForeground(Color.red);
        statusLabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
    }

    private void addButtonHandlers() {

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display("Connecting To server...");
                controller.connectToServer();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.disconnect();
            }
        });
    }
}
