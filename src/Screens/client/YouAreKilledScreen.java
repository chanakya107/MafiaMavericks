package screens.client;

import controllers.client.YouAreKilledController;
import screens.controls.MainFrame;
import view.client.YouAreKilledView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class YouAreKilledScreen implements YouAreKilledView {
    private final MainFrame mainFrame;
    private final YouAreKilledController controller;
    private final JPanel panel;
    private final JLabel label;

    public YouAreKilledScreen(MainFrame mainFrame, final YouAreKilledController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        panel = mainFrame.createPanel("thaiintelligentnews.wordpress.com1.jpg");

        label = new JLabel(controller.getName() + " You Are Killed!");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 90));
        label.setForeground(Color.RED);
        label.setBounds(100, 5, 700, 250);

        final JButton goToHome = new JButton("Go To Home ");
        panel.add(goToHome);
        goToHome.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        goToHome.setBounds(500, 500, 200, 40);

        goToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goToHome();
            }
        });
    }

    @Override
    public void displayLog(List<String> log) {
        final DefaultListModel<String> logDefaultList = new DefaultListModel<String>();
        final JList<String> logList = new JList<String>(logDefaultList);
        JScrollPane scrollPane = new JScrollPane(logList);
        panel.add(scrollPane);
        logList.setBackground(Color.GRAY);
        logList.setForeground(Color.WHITE);
        logList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        scrollPane.setBounds(50, 200, 350, 450);

        logDefaultList.removeAllElements();
        for (String log1 : log) {
            logDefaultList.addElement(log1);
        }
    }
}
