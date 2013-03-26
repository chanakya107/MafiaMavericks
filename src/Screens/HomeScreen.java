package screens;

import view.HomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen implements HomeView {
    private final screens.MainFrame mainframe;
    private final screens.HomeController controller;
    private final JButton startServer;
    private final JButton joinGame;
    private final JButton quit;

    public HomeScreen(screens.MainFrame mainframe, screens.HomeController controller) {
        this.mainframe = mainframe;
        this.controller = controller;



        frame = new JFrame("Mafia");
        firstPanel = new JPanel();
        frame.setVisible(true);
        firstPanel.setBackground(Color.black);
        firstPanel.setLayout(null);
        frame.setBounds(500, 300, 900, 700);
        frame.add(firstPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        screens.PanelImage panelImage = new screens.PanelImage(new ImageIcon(".\\Prototypes\\nice-cool-pics.com.jpeg").getImage());
        firstPanel.add(panelImage);
        panelImage.setSize(1000,1000);


        firstPanel.setBackground(panelImage.getBackground());
        panelImage.setVisible(true);
=======
        JPanel panel = mainframe.createPanel();
>>>>>>> 466107b08c82a3186208759c4bcd4679d2f6a97a

        startServer = new JButton("Start server");
        panel.add(startServer);
        startServer.setSize(150, 50);
        startServer.setLocation(100, 300);

        joinGame = new JButton("Join Game");
        panel.add(joinGame);
        joinGame.setSize(150, 50);
        joinGame.setLocation(100, 400);


        quit = new JButton("Quit");
        panel.add(quit);
        quit.setSize(150, 50);
        quit.setLocation(100, 500);

        addButtonHandlers();
    }

    private void addButtonHandlers() {
        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controller.startServer();
//                homePanel.setVisible(false);
//                new StartGameScreen(frame, homePanel).display();
            }
        });

        joinGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.joinGame();
//                homePanel.setVisible(false);
//                new ClientDetailsScreen(frame, homePanel).joinGame();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you really want to Quit?", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}
