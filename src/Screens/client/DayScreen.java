package screens.client;

import controllers.client.DayController;
import controllers.server.Player;
import screens.controls.MainFrame;
import view.client.DayView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class DayScreen implements DayView {
    private final DayController controller;
    private final JList<String> playerList;
    private final JLabel playerKilledLabel;
    private JPanel panel;
    private String selectedPlayer;
    private JLabel timer;
    private DefaultListModel<String> playersDefaultList;

    public DayScreen(MainFrame mainFrame, DayController controller) {
        this.controller = controller;

        panel = mainFrame.createPanel("hdwallpapersbase.com.jpg");

        timer = new JLabel("");

        JLabel label = new JLabel("Day Arrived..");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setSize(250, 150);
        label.setLocation(130, 25);

        playersDefaultList = new DefaultListModel<String>();

        playerList = new JList<String>(playersDefaultList);
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setBounds(100, 130, 250, 400);

        playerKilledLabel = new JLabel(controller.getKilledPlayer() + " is Killed");
        panel.add(playerKilledLabel);
        playerKilledLabel.setFont(new Font("Chiller", Font.PLAIN, 90));
        playerKilledLabel.setForeground(Color.WHITE);
        playerKilledLabel.setBounds(700, 25, 500, 250);
    }

    @Override
    public void display() {
        displayVoting();
        displayPlayerList();
    }

    private void displayVoting() {
        JRadioButton radioButton;
        ButtonGroup buttonGroup = new ButtonGroup();

        int xAxis = 750, yAxis = 450, width = 150, height = 50;

        List<Player> players = controller.getPlayers();
        for (Player player : players) {
            String playerName = player.getName();
            radioButton = new JRadioButton(playerName);
            radioButton.setActionCommand(String.valueOf(player));
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
                    AbstractButton button;
                    button = (AbstractButton) ev.getItemSelectable();
                    selectedPlayer = button.getActionCommand();
//                    controller.voteChanged();
                }
            });
        }
        selectedPlayer = buttonGroup.getSelection().getActionCommand();
        panel.repaint();
    }

    private void displayPlayerList() {
        playersDefaultList.removeAllElements();
        List<Player> mafias = controller.getPlayers();
        for (Player mafia : mafias) {
            playersDefaultList.addElement(mafia.getName());
        }
    }

    @Override
    public void displayTimer(int count) {
        panel.remove(timer);
        timer.setText(String.valueOf(count));
        panel.add(timer);
        timer.setFont(new Font("Chiller", Font.PLAIN, 90));
        timer.setForeground(Color.WHITE);
        timer.setBounds(950, 450, 150, 150);
    }

    public Player getSelectedPlayer(List<Player> players) {
        Player playerSelected = null;
        for (Player player : players) {
            if (String.valueOf(player).equals(selectedPlayer)) {
                playerSelected = player;
            }
        }
        return playerSelected;
    }
}