package screens.client;

import controllers.client.ClientDetailsController;
import screens.controls.MainFrame;
import view.client.ClientDetailsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientDetailsScreen implements ClientDetailsView {

    private MainFrame mainFrame;
    private ClientDetailsController controller;
    private JPanel panel;
    private JLabel enterServerName;
    private JLabel textFieldBlank;
    private JTextField serverNameField;
    private JLabel enterUserName;
    private JButton connect;
    private JButton cancel;
    private JTextField userNameField;
    private JLabel statusLabel;


    public ClientDetailsScreen(MainFrame mainFrame, ClientDetailsController controller) {

        this.mainFrame = mainFrame;
        this.controller = controller;
        statusLabel = new JLabel();

        Image image = new ImageIcon(".\\Images\\hdwallpapersbase.com.jpg").getImage();
        panel = mainFrame.createPanel(image);

        enterServerName = new JLabel("Enter the Server Name :");
        panel.add(enterServerName);

        enterServerName.setSize(300, 150);
        enterServerName.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        enterServerName.setLocation(100, 100);

        serverNameField = new JTextField("");
        panel.add(serverNameField);
        serverNameField.setSize(200, 30);
        serverNameField.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        serverNameField.setLocation(400, 160);

        enterUserName = new JLabel("Enter the User Name :");
        panel.add(enterUserName);
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
        connect.setLocation(400, 500);


        cancel = new JButton("Cancel");
        panel.add(cancel);
        cancel.setSize(150, 50);
        cancel.setLocation(600, 500);

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
        statusLabel.setBounds(400, 400, 1000, 150);
        statusLabel.setForeground(Color.red);
        statusLabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
    }

    private void addButtonHandlers() {

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display("Connecting To Server...");
                controller.connectToServer();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Cancel ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.disconnect();
                }
            }
        });
    }
}


