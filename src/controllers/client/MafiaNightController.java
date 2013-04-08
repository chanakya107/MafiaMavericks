package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import controllers.server.Player;
import messages.PlayerVotedMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MafiaNightController extends VillagerNightController {
    Timer timer;

    public MafiaNightController(Workflow workflow, SocketChannel channel, List<Player> players, Player currentPlayer) {
        super(workflow, channel, players, currentPlayer);
    }

    public void start() {
        view.displayAtNight();
        startVoting();
    }

    private void startVoting() {
        Runnable runner = new Runnable() {
            public void run() {
                timer = new Timer(1000, new ActionListener() {
                    int count = 30;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        count--;
                        view.displayTimer(count);
                        if (count == 0) {
                            ((Timer) e.getSource()).stop();

                            channel.send(new PlayerVotedMessage(getCurrentPlayer(),view.getSelectedPlayer(players),players,getMafiaList().size()));

                        }
                    }
                });
                timer.start();
            }
        };
        EventQueue.invokeLater(runner);
    }

    public void voteChanged() {
        channel.send(new PlayerVotedMessage(getCurrentPlayer(),view.getSelectedPlayer(players), players, getMafiaList().size()));
    }
}
