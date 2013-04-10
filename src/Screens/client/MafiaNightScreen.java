package screens.client;

import controllers.client.MafiaNightController;
import controllers.server.Player;
import screens.controls.MainFrame;
import view.client.MafiaNightView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.List;

public class MafiaNightScreen implements MafiaNightView {
    private final MafiaNightController controller;
    private final JPanel panel;
    private final JList<String> playerList;
    private final JLabel label;
    private final DefaultListModel<String> playersDefaultList;
    private final JButton confirm;
    private final JLabel label1;
    private String selectedPlayer;
    private ButtonGroup buttonGroup;

    public MafiaNightScreen(MainFrame mainFrame, final MafiaNightController controller) {
        this.controller = controller;
        controller.bind(this);

        panel = mainFrame.createPanel("if.invisionfree.com.jpg");

        JLabel instruction = new JLabel("Select the player whom you want to kill and press Confirm");
        instruction.setFont(new Font("Chiller", Font.PLAIN, 40));
        instruction.setForeground(Color.yellow);
        panel.add(instruction);
        instruction.setBounds(50, 550, 800, 50);

        confirm = new JButton("Confirm");
        panel.add(confirm);
        confirm.setBackground(Color.white);
        confirm.setBounds(500, 250, 150, 50);
        confirm.setEnabled(true);

        label1 = new JLabel("Night Arrived");
        panel.add(label1);
        label1.setFont(new Font("Chiller", Font.PLAIN, 50));
        label1.setForeground(Color.WHITE);
        label1.setBounds(400, 10, 250, 150);

        label = new JLabel("Other Mafias");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setBounds(50, 25, 250, 150);

        playersDefaultList = new DefaultListModel<String>();

        playerList = new JList<String>(playersDefaultList);
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setBounds(30, 130, 250, 400);

        JLabel nameLabel = new JLabel(controller.getCurrentPlayer().getName() + " - " + controller.getCurrentPlayer().getRole());
        panel.add(nameLabel);
        nameLabel.setFont(new Font("Chiller", Font.PLAIN, 50));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(500, 2, 500, 250);

        addButtonListeners();
    }

    private void addButtonListeners() {
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startVoting();
            }
        });
    }

    @Override
    public void display() {
        displayMafiaList();
        displayPlayersList();
    }

    private void displayPlayersList() {
        JRadioButton radioButton;
        buttonGroup = new ButtonGroup();

        int xAxis = 300, yAxis = 150, width = 150, height = 35;

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

    private void displayMafiaList() {
        playersDefaultList.removeAllElements();
        List<Player> mafias = controller.getMafiaList();
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
