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

        JLabel gameOver =new JLabel("Game Over");
        panel.add(gameOver);
        gameOver.setFont(new Font("Comic Sans Ms", Font.PLAIN, 80));
        gameOver.setForeground(Color.blue);
        gameOver.setBounds(100, 10, 600, 100);

        JButton goToHome = new JButton("Go To Home");
        panel.add(goToHome);
        goToHome.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        goToHome.setBackground(Color.BLUE);
        goToHome.setForeground(Color.white);
        goToHome.setBounds(150, 600, 200, 40);

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
        label.setFont(new Font("Chiller", Font.PLAIN, 60));
        label.setForeground(Color.BLUE);
        label.setBounds(100, 80, 900, 250);
    }

    @Override
    public void displayLog(java.util.List<String> log) {
        final DefaultListModel<String> logDefaultList = new DefaultListModel<String>();
        final JList<String> logList = new JList<String>(logDefaultList);
        JScrollPane scrollPane = new JScrollPane(logList);
        panel.add(scrollPane);
        logList.setBackground(Color.GRAY);
        logList.setForeground(Color.WHITE);
        logList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        scrollPane.setBounds(370, 230, 350, 450);

        logDefaultList.removeAllElements();
        for (String log1 : log) {
            logDefaultList.addElement(log1);
        }
    }
}
