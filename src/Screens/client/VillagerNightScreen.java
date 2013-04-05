package screens.client;

import controllers.client.VillagerNightController;
import controllers.client.Vote;
import screens.controls.MainFrame;
import view.client.VillagerNightView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class VillagerNightScreen implements VillagerNightView {
    private final MainFrame mainFrame;
    private final VillagerNightController controller;
    private final JPanel panel;
    private final JButton disconnect;
    private JLabel label;

    public VillagerNightScreen(MainFrame mainFrame, final VillagerNightController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        controller.bind(this);

        Image image = new ImageIcon(".\\Images\\www.desktopwallpapers4.me.jpg").getImage();
        panel = mainFrame.createPanel(image);


        JRadioButton radioButton;
        ButtonGroup buttonGroup = new ButtonGroup();

        int xAxis = 750, yAxis = 450, width = 150, height = 50;
        final ArrayList<String> players = new ArrayList<String>();
        players.add("deepthi");
        players.add("raghav");

        for (int i = 0; i < players.size(); i++) {
            String player = players.get(i);
            radioButton = new JRadioButton(player);
            radioButton.setSize(width, height);
            radioButton.setLocation(xAxis, yAxis);
            radioButton.setSelected(false);
            panel.add(radioButton);
            buttonGroup.add(radioButton);

            yAxis += 80;
            radioButton.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent ev) {
                    boolean selected = (ev.getStateChange() == ItemEvent.SELECTED);
                    AbstractButton button = (AbstractButton) ev.getItemSelectable();
                    String selectedName = button.getActionCommand();
                    System.out.println("ITEM Choice Selected: " + selected
                            + ", Selection: " + selectedName);

                    Vote vote = new Vote(players, selectedName);
                    int voteCount = 0;
                    for (String s : vote.count()) {
                        String[] temp;
                        temp = s.split(" ");
                        voteCount = voteCount + Integer.parseInt(temp[1]);
                    }
                }
            });
        }

        disconnect = new JButton("Disconnect");
        panel.add(disconnect);
        disconnect.setSize(150, 50);
        disconnect.setLocation(950, 550);

        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you really want to Disconnect ?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.disconnectingFromServer();
                }
            }
        });
    }

    @Override
    public void displayAtNight() {
        label = new JLabel("Night Arrived");
        panel.add(label);
        label.setFont(new Font("Chiller", Font.PLAIN, 50));
        label.setForeground(Color.WHITE);
        label.setBounds(130, 25, 250, 150);
    }

    @Override
    public void displayAtDay() {
    }

    @Override
    public void displayTimer(int count) {
    }

    @Override
    public String getSelectedPlayer() {
        return "";
    }
}
