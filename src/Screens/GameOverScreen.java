package screens;

import controllers.GameOverController;
import controllers.server.Role;
import screens.controls.MainFrame;
import view.GameOverView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverScreen implements GameOverView {
    private final MainFrame mainFrame;
    private final GameOverController controller;
    private final JPanel panel;

    public GameOverScreen(MainFrame mainFrame, final GameOverController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createPanel("findwallpaper.info.jpg");
        JButton goToHome = new JButton("Go To Home");
        panel.add(goToHome);
        goToHome.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        goToHome.setBackground(Color.BLACK);
        goToHome.setForeground(Color.white);
        goToHome.setBounds(1000, 700, 180, 40);

        goToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goToHome();
            }
        });
    }

    @Override
    public void displayWinner(Role winner) {
        JLabel label = new JLabel(winner + "s won the Game ...");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 90));
        label.setForeground(Color.BLACK);
        label.setBounds(300, 600, 900, 250);
    }
}
