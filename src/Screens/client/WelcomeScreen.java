package screens.client;

import controllers.client.WelcomeController;
import screens.controls.MainFrame;
import view.client.WelcomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen implements WelcomeView {
    private final MainFrame mainFrame;
    private final WelcomeController controller;
    private final JPanel panel;
    private final JButton disconnect;

    public WelcomeScreen(MainFrame mainFrame, final WelcomeController controller) {
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
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Disconnect ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.disconnectingFromServer();
                }
            }
        });
    }
}
