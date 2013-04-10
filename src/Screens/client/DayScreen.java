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
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.red);
        label.setSize(250, 150);
        label.setLocation(50,2);


        JLabel playersPresent = new JLabel("Players Present");
        panel.add(playersPresent);
        playersPresent.setFont(new Font("Chiller", Font.PLAIN, 50));
        playersPresent.setForeground(Color.WHITE);
        playersPresent.setSize(250, 150);
        playersPresent.setLocation(50,10);

        JLabel instruction = new JLabel("Select the player whom you want do you suspect as Mafia");
        instruction.setFont(new Font("Chiller", Font.PLAIN, 40));
        instruction.setForeground(Color.WHITE);
        panel.add(instruction);
        instruction.setBounds(50, 500, 800, 50);

        JLabel instruction1 = new JLabel("Press Confirm to kill them");
        instruction1.setFont(new Font("Chiller", Font.PLAIN, 40));
        instruction1.setForeground(Color.WHITE);
        panel.add(instruction1);
        instruction1.setBounds(50,550, 800, 50);

        confirm = new JButton("Confirm");
        panel.add(confirm);
        confirm.setBounds(550, 250, 150, 50);

        playersDefaultList = new DefaultListModel<String>();

        playerList = new JList<String>(playersDefaultList);
        panel.add(playerList);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setBackground(Color.gray);
        playerList.setForeground(Color.WHITE);
        playerList.setVisible(true);
        playerList.setBounds(30, 80, 250, 400);

        JLabel nameLabel = new JLabel(controller.getCurrentPlayer().getName() + " - " + controller.getCurrentPlayer().getRole());
        panel.add(nameLabel);
        nameLabel.setFont(new Font("Chiller", Font.PLAIN, 30));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(500, 2, 500, 250);

        playerKilledLabel = new JLabel(controller.getKilledPlayer() + " is Killed");
        panel.add(playerKilledLabel);
        playerKilledLabel.setFont(new Font("Chiller", Font.PLAIN, 50));
        playerKilledLabel.setForeground(Color.WHITE);
        playerKilledLabel.setBounds(250, 30, 500, 250);

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

        int xAxis = 350, yAxis = 100, width = 100, height = 30;
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

            yAxis += 50;

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

    @Override
    public void displayLog(List<String> log) {
        final DefaultListModel<String> logDefaultList = new DefaultListModel<String>();
        final JList<String> logList = new JList<String>(logDefaultList);
        JScrollPane scrollPane = new JScrollPane(logList);
        panel.add(scrollPane);
        logList.setBackground(Color.GRAY);
        logList.setForeground(Color.WHITE);
        logList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        scrollPane.setBounds(370, 110, 350, 450);

        logDefaultList.removeAllElements();
        for (String log1 : log) {
            logDefaultList.addElement(log1);
        }
    }

    private void disableButtons() {
        Enumeration<AbstractButton> elements = buttonGroup.getElements();
        while (elements.hasMoreElements()) {
            AbstractButton button = elements.nextElement();
            button.setEnabled(false);
        }
    }
}