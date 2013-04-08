package screens;

import controllers.GameOverController;
import controllers.server.Role;
import screens.controls.MainFrame;
import view.GameOverView;

import javax.swing.*;
import java.awt.*;

public class GameOverScreen implements GameOverView {
    private final MainFrame mainFrame;
    private final GameOverController controller;
    private final JPanel panel;

    public GameOverScreen(MainFrame mainFrame, GameOverController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        Image image = new ImageIcon(".\\Images\\findwallpaper.info.jpg").getImage();
        panel = mainFrame.createPanel(image);
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
