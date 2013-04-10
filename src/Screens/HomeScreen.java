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

        panel = mainframe.createPanel("nice-cool-pics.com.jpg");

        startServer = new JButton("Start server");
        panel.add(startServer);
        startServer.setSize(150, 50);
        startServer.setLocation(300, 200);

        joinGame = new JButton("Join Game");
        panel.add(joinGame);
        joinGame.setSize(150, 50);
        joinGame.setLocation(300, 300);


        quit = new JButton("Quit");
        panel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(300, 400);

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
    public void displayError(String message) {
        JLabel label = new JLabel(message);
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.RED);
        label.setSize(600, 150);
        label.setLocation(150, 450);
    }
}
