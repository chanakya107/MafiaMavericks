package UserScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGameScreen {
    public void joinGame(final JFrame frame) {
        frame.setVisible(false);
        final JFrame joinFrame = new JFrame("Mafia");
        joinFrame.setVisible(true);
        joinFrame.setBounds(500, 300, 900, 700);
        JPanel joinPanel = new JPanel();
        joinPanel.setBackground(Color.black);
        joinPanel.setLayout(null);


        JButton cancel = new JButton("Cancel");

        joinPanel.add(cancel);
        cancel.setSize(150, 50);
        cancel.setLocation(600, 500);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Cancel ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    joinFrame.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        joinFrame.setContentPane(joinPanel);
        joinFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
