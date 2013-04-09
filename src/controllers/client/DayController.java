package controllers.client;

import channels.SocketChannel;
import controllers.Phase;
import controllers.Workflow;
import controllers.server.Player;
import messages.PlayerVotedMessage;
import view.client.DayView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DayController {

    private final Workflow workflow;
    private final String killedPlayer;
    private final List<Player> players;
    private Player currentPlayer;
    private SocketChannel channel;
    private Timer timer;
    private DayView view;

    public DayController(Workflow workflow, String killedPlayer, List<Player> players, Player currentPlayer, SocketChannel channel) {
        this.workflow = workflow;
        this.killedPlayer = killedPlayer;
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.channel = channel;
    }

    public void start() {
        view.display();
        startVoting();
    }

    public void bind(DayView view) {
        this.view = view;
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
                            channel.send(new PlayerVotedMessage(currentPlayer.getName(), view.getSelectedPlayer(players), players, players.size(), Phase.Day));
                        }
                    }
                });
                timer.start();
            }
        };
        EventQueue.invokeLater(runner);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getKilledPlayer() {
        return killedPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
