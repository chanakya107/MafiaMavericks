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
        panel = mainFrame.createPanel("www.bimmerfest.jpg");
        JButton goToHome = new JButton("Go Back");
        panel.add(goToHome);
        goToHome.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        goToHome.setBackground(Color.BLUE);
        goToHome.setForeground(Color.white);
        goToHome.setBounds(550, 800, 180, 40);

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
        label.setForeground(Color.BLUE);
        label.setBounds(300, 600, 900, 250);
    }
}
