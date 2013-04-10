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
import java.util.Enumeration;
import java.util.List;

public class DayScreen implements DayView {
    private final DayController controller;
    private final JList<String> playerList;
    private final JLabel playerKilledLabel;
    private final JButton confirm;
    private JPanel panel;
    private String selectedPlayer;
    private DefaultListModel<String> playersDefaultList;
    private ButtonGroup buttonGroup;

    public DayScreen(MainFrame mainFrame, DayController controller) {
        this.controller = controller;

        panel = mainFrame.createPanel("hdwallpapersbase.com.jpg");

        JLabel label = new JLabel("Day Arrived..");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 60));
        label.setForeground(Color.red);
        label.setSize(250, 150);
        label.setLocation(130, 20);

        JLabel playerListLabel= new JLabel("Player's list");
        panel.add(playerListLabel);
        playerListLabel.setFont(new Font("Chiller", Font.PLAIN, 40));
        playerListLabel.setForeground(Color.WHITE);
        playerListLabel.setSize(250, 150);
        playerListLabel.setLocation(130, 70);

        confirm = new JButton("Confirm");
        panel.add(confirm);
        confirm.setBounds(950, 450, 150, 50);
        confirm.setEnabled(true);

        playersDefaultList = new DefaultListModel<String>();

        playerList = new JList<String>(playersDefaultList);
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setBounds(100, 180, 250, 400);

        playerKilledLabel = new JLabel(controller.getKilledPlayer() + " is Killed");
        panel.add(playerKilledLabel);
        playerKilledLabel.setFont(new Font("Chiller", Font.PLAIN, 90));
        playerKilledLabel.setForeground(Color.WHITE);
        playerKilledLabel.setBounds(700, 25, 500, 250);

        JLabel nameLabel = new JLabel(controller.getCurrentPlayer().getName() + " - " + controller.getCurrentPlayer().getRole());
        panel.add(nameLabel);
        nameLabel.setFont(new Font("Chiller", Font.PLAIN, 50));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(950,5, 500, 250);

        addButtonListener();
    }
   private void addButtonListener() {
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startVoting();
            }
        });

    }

    @Override
    public void display() {
        displayVoting();
        displayPlayerList();
    }

    private void displayVoting() {
        JRadioButton radioButton;
        buttonGroup = new ButtonGroup();

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
    public String getSelectedPlayer() {
        return selectedPlayer;
    }

    @Override
    public void disableConfirm() {
        confirm.setEnabled(false);
        disableButtons();
    }

    private void disableButtons() {
        Enumeration<AbstractButton> elements = buttonGroup.getElements();
        while (elements.hasMoreElements()) {
            AbstractButton button = elements.nextElement();
            button.setEnabled(false);
        }
    }
}