package UserScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGameScreen {
    public void joinGame(final JFrame frame, final JPanel firstPanel) {
        final JPanel joinPanel = new JPanel();
        frame.add(joinPanel);
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
                    joinPanel.setVisible(false);
                    firstPanel.setVisible(true);
                }
            }
        });
    }

}
