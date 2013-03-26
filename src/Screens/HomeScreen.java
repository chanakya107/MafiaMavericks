package screens;

import view.HomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen implements HomeView {
    private final MainFrame mainframe;
    private final HomeController controller;
    private final JButton startServer;
    private final JButton joinGame;
    private final JButton quit;

    public HomeScreen(MainFrame mainframe, HomeController controller) {
        this.mainframe = mainframe;
        this.controller = controller;
        controller.bind(this);


        Image image = new ImageIcon(".\\Prototypes\\nice-cool-pics.com.jpeg").getImage();
        JPanel panel = mainframe.createPanel(image);

        startServer = new JButton("Start server");
        panel.add(startServer);
        startServer.setSize(150, 50);
        startServer.setLocation(100, 300);

        joinGame = new JButton("Join Game");
        panel.add(joinGame);
        joinGame.setSize(150, 50);
        joinGame.setLocation(100, 400);


        quit = new JButton("Quit");
        panel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(100, 500);

        addButtonHandlers();
    }

    private void addButtonHandlers() {
        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controller.startServer();
            }
        });

        joinGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.joinGame();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you really want to Quit?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}
