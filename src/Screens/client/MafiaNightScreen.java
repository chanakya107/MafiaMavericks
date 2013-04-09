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
import java.util.List;

public class MafiaNightScreen implements MafiaNightView {
    private final MafiaNightController controller;
    private final JPanel panel;
    private final JButton disconnect;
    private final JList<String> playerList;
    private final JLabel label;
    private final DefaultListModel<String> playersDefaultList;
    private JLabel timer;
    private String selectedPlayer;

    public MafiaNightScreen(MainFrame mainFrame, final MafiaNightController controller) {
        this.controller = controller;
        controller.bind(this);

        panel = mainFrame.createPanel("if.invisionfree.com.jpg");

        disconnect = new JButton("Disconnect");
        panel.add(disconnect);
        disconnect.setBounds(950, 550, 150, 50);

        timer = new JLabel("");

        label = new JLabel("Mafias");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setBounds(130, 25, 250, 150);

        playersDefaultList = new DefaultListModel<String>();

        playerList = new JList<String>(playersDefaultList);
        panel.add(playerList);
        playerList.setBackground(Color.GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));
        playerList.setBounds(100, 130, 250, 400);
        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to really Disconnect ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.disconnectingFromServer();
                }
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

    private void displayMafiaList() {
        playersDefaultList.removeAllElements();
        List<Player> mafias = controller.getMafiaList();
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

    @Override
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