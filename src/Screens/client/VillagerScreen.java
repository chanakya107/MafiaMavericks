package screens.client;

import controllers.client.VillagerController;
import screens.controls.MainFrame;
import view.client.VillagerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VillagerScreen implements VillagerView {
    private final MainFrame mainFrame;
    private final VillagerController controller;
    private final JPanel panel;
    private final JButton disconnect;

    public VillagerScreen(MainFrame mainFrame, final VillagerController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        controller.bind(this);

        Image image = new ImageIcon(".\\Prototypes\\www.desktopwallpapers4.me.jpg").getImage();
        panel = mainFrame.createPanel(image);

        disconnect = new JButton("Disconnect");
        panel.add(disconnect);
        disconnect.setSize(150, 50);
        disconnect.setLocation(950, 550);

        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you really want to Disconnect ?", "", JOptionPane.YES_NO_OPTION);
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
