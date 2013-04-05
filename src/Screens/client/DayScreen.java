package screens.client;

import controllers.client.DayController;
import controllers.server.Player;
import screens.controls.MainFrame;
import view.client.DayView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class DayScreen implements DayView {
    private final MainFrame mainFrame;
    private final DayController controller;
    private JPanel panel;

    public DayScreen(MainFrame mainFrame, DayController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        Image image = new ImageIcon(".\\Images\\hdwallpapersbase.com.jpg").getImage();
        panel = mainFrame.createPanel(image);

        JLabel label = new JLabel("Day Arrived..");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setSize(250, 150);
        label.setLocation(130, 25);

    }


    private void dayPlayerList() {
        JRadioButton radioButton;
        ButtonGroup buttonGroup = new ButtonGroup();

        int xAxis = 750, yAxis = 450, width = 150, height = 50;

        List<Player> players = controller.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            String player = players.get(i).getName();
            radioButton = new JRadioButton(player);
            radioButton.setActionCommand(player);
            radioButton.setSize(width, height);
            radioButton.setLocation(xAxis, yAxis);
            radioButton.setSelected(false);
            if (player.equals(controller.getCurrentPlayer()))
                radioButton.setSelected(true);
            buttonGroup.add(radioButton);
            panel.add(radioButton);

            yAxis += 80;
            radioButton.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ev) {
                    boolean selected = (ev.getStateChange() == ItemEvent.SELECTED);
                    AbstractButton button = (AbstractButton) ev.getItemSelectable();
                    String selectedName = button.getActionCommand();
                    System.out.println("ITEM Choice Selected: " + selected + ", Selection: " + selectedName);
                }
            });
        }
        System.out.println("hiiiiiiiiiiiii i am selected " + buttonGroup.getSelection().getActionCommand());
        panel.repaint();
    }


    @Override
    public void displayKilledPlayer() {
        JLabel label = new JLabel("Killed Player : " + controller.getKilledPlayer());
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.RED);
        label.setSize(500, 300);
        label.setLocation(130, 25);
    }

    @Override
    public void displayVoting() {
        dayPlayerList();
    }

}