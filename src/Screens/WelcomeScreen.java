package screens;

import view.WelcomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen implements WelcomeView {

    public void display(JFrame frame) {
        JPanel welcomePanel = new JPanel();
        frame.add(welcomePanel);
        welcomePanel.setBackground(Color.black);
        welcomePanel.setLayout(null);

        JButton quit = new JButton("Quit");
        welcomePanel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(650, 550);

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
