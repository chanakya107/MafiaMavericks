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


        JLabel playersPresent = new JLabel("Players Present");
        panel.add(playersPresent);
        playersPresent.setFont(new Font("Chiller", Font.PLAIN, 50));
        playersPresent.setForeground(Color.WHITE);
        playersPresent.setSize(250, 150);
        playersPresent.setLocation(130, 130);

        JLabel instruction = new JLabel("Select the player whom you want do you suspect as Mafia");
        instruction.setFont(new Font("Chiller", Font.PLAIN, 40));
        instruction.setForeground(Color.WHITE);
        panel.add(instruction);
        instruction.setBounds(500, 350, 800, 50);

        JLabel instruction1 = new JLabel("Press Confirm to kill them");
        instruction1.setFont(new Font("Chiller", Font.PLAIN, 40));
        instruction1.setForeground(Color.WHITE);
        panel.add(instruction1);
        instruction1.setBounds(500, 400, 800, 50);

        confirm = new JButton("Confirm");
        panel.add(confirm);
        confirm.setBounds(950, 450, 150, 50);

        playersDefaultList = new DefaultListModel<String>();

        playerList = new JList<String>(playersDefaultList);
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);

        JLabel nameLabel = new JLabel(controller.getCurrentPlayer().getName() + " - " + controller.getCurrentPlayer().getRole());
        panel.add(nameLabel);
        nameLabel.setFont(new Font("Chiller", Font.PLAIN, 50));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(950,5, 500, 250);

        playerKilledLabel = new JLabel(controller.getKilledPlayer() + " is Killed");
        panel.add(playerKilledLabel);
        playerKilledLabel.setFont(new Font("Chiller", Font.PLAIN, 60));
        playerKilledLabel.setForeground(Color.WHITE);
        playerKilledLabel.setBounds(950, 100, 500, 250);

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