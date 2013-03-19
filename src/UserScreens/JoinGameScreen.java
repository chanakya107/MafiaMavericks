package UserScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGameScreen {
    public void joinGame(final JFrame frame) {
        frame.setVisible(false);
        final JFrame joinFrame =new JFrame("Mafia");
        joinFrame.setVisible(true);
        joinFrame.setBounds(500, 300, 900, 700);
        JPanel joinPanel = new JPanel();
        joinPanel.setBackground(Color.black);
        joinPanel.setLayout(null);

        JButton back = new JButton("Back");

        joinPanel.add(back);
        back.setSize(150, 50);
        back.setLocation(600, 500);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinFrame.setVisible(false);
                frame.setVisible(true);
            }
        });
        joinFrame.setContentPane(joinPanel);
        joinFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
