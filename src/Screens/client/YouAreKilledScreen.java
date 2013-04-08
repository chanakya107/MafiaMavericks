package screens.client;

import controllers.client.YouAreKilledController;
import screens.controls.MainFrame;
import view.client.YouAreKilledView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YouAreKilledScreen implements YouAreKilledView {
    private final MainFrame mainFrame;
    private final YouAreKilledController controller;
    private final JPanel panel;
    private final JLabel label;

    public YouAreKilledScreen(MainFrame mainFrame, final YouAreKilledController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createPanel("thaiintelligentnews.wordpress.com1.jpg");

        label = new JLabel("You are Killed");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 90));
        label.setForeground(Color.WHITE);
        label.setBounds(130, 25, 500, 250);

        final JButton goToHome = new JButton("Go to home");
        panel.add(goToHome);
        goToHome.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        goToHome.setBounds(900, 700, 180, 40);

        goToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goToHome();
            }
        });
    }
}
