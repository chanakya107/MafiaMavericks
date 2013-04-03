package screens.client;

import controllers.client.MafiaNightController;
import screens.controls.MainFrame;
import view.client.MafiaNightView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MafiaNightScreen implements MafiaNightView {
    private final MainFrame mainFrame;
    private final MafiaNightController controller;
    private final JPanel panel;
    private final JButton disconnect;

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

}


