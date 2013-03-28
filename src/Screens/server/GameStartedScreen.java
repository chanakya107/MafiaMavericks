package screens.server;

import controllers.server.GameStartedController;
import screens.controls.MainFrame;
import view.server.GameStartedView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStartedScreen implements GameStartedView {

    private final MainFrame mainFrame;
    private GameStartedController controller;
    private final JPanel panel;
    private final JButton quit;

    public GameStartedScreen(MainFrame mainFrame, final GameStartedController controller) {

        this.mainFrame = mainFrame;
        this.controller = controller;
        controller.bind(this);

        Image image = new ImageIcon(".\\Prototypes\\www.desktopwallpapers4.me.jpg").getImage();
        panel = mainFrame.createPanel(image);

        quit = new JButton("Quit");
        panel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(950, 550);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Quit ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
//                    controller.stopServer();
                    System.exit(0);
                }
            }
        });

    }
}
