package Screens;

import Channels.Messages.ChannelMessage;
import Channels.SocketChannel;
import Channels.SocketChannelListener;
import View.ClientDetailsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientDetailsScreen implements SocketChannelListener, ClientDetailsView {
    SocketChannel client;

    public void joinGame(final JFrame frame, final JPanel firstPanel, final String serverName) {
        final JPanel joinPanel = new JPanel();
        frame.add(joinPanel);
        joinPanel.setBackground(Color.black);
        joinPanel.setLayout(null);

        JLabel enterServerName = new JLabel("Enter the Server Name :");

        joinPanel.add(enterServerName);
        enterServerName.setSize(300, 150);
        enterServerName.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        enterServerName.setLocation(100, 100);

        final JTextField serverNameField = new JTextField();

        joinPanel.add(serverNameField);
        serverNameField.setSize(200, 30);
        serverNameField.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        serverNameField.setLocation(400, 160);

        final JLabel enterUserName = new JLabel("Enter the User Name :");

        joinPanel.add(enterUserName);
        enterUserName.setSize(300, 150);
        enterUserName.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        enterUserName.setLocation(100, 200);


        final JTextField userNameField = new JTextField(null);

        joinPanel.add(userNameField);
        userNameField.setSize(200, 30);
        userNameField.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        userNameField.setLocation(400, 260);


        JButton connect = new JButton("Connect");

        joinPanel.add(connect);
        connect.setSize(150, 50);
        connect.setLocation(400, 500);
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinPanel.setVisible(false);


                String text;
                text = serverNameField.getText();
                System.out.println("hai : '" + text + "'");
                if (text.equals("")) {
                    joinPanel.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Connection Failed");
                } else {
                    connectTo(serverNameField.getText());
                    new JoinGameScreen().display(frame);
                    JOptionPane.showMessageDialog(null, "Connected to Server");
                }


            }
        });

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
        try {
            SocketChannel.connectTo(serverName, 1234, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        this.client = channel;
        client.bind(this);
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {

    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
    }
}

