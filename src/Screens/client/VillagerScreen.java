package screens.client;

import controllers.client.VillagerController;
import controllers.client.Vote;
import screens.controls.MainFrame;
import view.client.VillagerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class VillagerScreen implements VillagerView {
    private final MainFrame mainFrame;
    private final VillagerController controller;
    private final JPanel panel;
    private final JButton disconnect;


    public VillagerScreen(MainFrame mainFrame, final VillagerController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        controller.bind(this);

        Image image = new ImageIcon(".\\Images\\www.desktopwallpapers4.me.jpg").getImage();
        panel = mainFrame.createPanel(image);

        String deepthi = "Deepthi";
        String raghu = "Raghu";

        final List<String> players = new ArrayList<String>();
        players.add(deepthi);
        players.add(raghu);
        JRadioButton radioButton;
        ButtonGroup buttonGroup = new ButtonGroup();

        int xAxis = 750 , yAxis = 450 , width = 150 , height = 50;

        for (int i = 0; i < players.size(); i++) {
            String player = players.get(i);
            System.out.println("hai" + players.size());
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
                    Vote vote = new Vote(players,selectedName);
                    vote.count();
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
    public void serverDisconnected(String serverName) {
        JOptionPane.showConfirmDialog(null, "Connection to server : " + serverName + " is lost", "", JOptionPane.DEFAULT_OPTION);
        controller.goToHome();
    }


}
