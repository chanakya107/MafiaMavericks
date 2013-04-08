package screens;

import controllers.HomeController;
import screens.controls.MainFrame;
import view.HomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen implements HomeView {
    private final HomeController controller;
    private final JButton startServer;
    private final JButton joinGame;
    private final JButton quit;
    private JPanel panel;

    public HomeScreen(MainFrame mainframe, HomeController controller) {
        this.controller = controller;
        controller.bind(this);


        Image image = new ImageIcon(".\\Images\\nice-cool-pics.com.jpg").getImage();
        panel = mainframe.createPanel(image);

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
                controller.getPlayerDetails();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void serverDisconnected(String serverName) {
        JLabel label = new JLabel("Connection to server : " + serverName + " is lost");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.RED);
        label.setSize(600, 150);
        label.setLocation(100, 550);
    }
}
