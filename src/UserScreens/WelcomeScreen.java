package UserScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen {

    public void display(JFrame serverFrame) {
        serverFrame.setVisible(false);
        final JFrame welcomeFrame = new JFrame("Mafia");
        welcomeFrame.setVisible(true);
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.black);
        welcomePanel.setLayout(null);

        JButton quit = new JButton("Quit");
        welcomePanel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(650, 550);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        welcomeFrame.setContentPane(welcomePanel);
        welcomeFrame.setBounds(500, 300, 900, 700);
        welcomeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
