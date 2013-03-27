package screens.client;

import controllers.client.WelcomeController;
import screens.controls.MainFrame;
import view.WelcomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen implements WelcomeView {

    private final MainFrame mainFrame;
    private WelcomeController controller;
    private final JPanel panel;

    public WelcomeScreen(MainFrame mainFrame, WelcomeController controller) {

        this.mainFrame = mainFrame;
        this.controller = controller;
        controller.bind(this);

        Image image = new ImageIcon(".\\Prototypes\\www.desktopwallpapers4.me.jpg").getImage();
        panel = mainFrame.createPanel(image);

        JButton quit = new JButton("Quit");
        panel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(950, 550);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Quit ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

    }
}
