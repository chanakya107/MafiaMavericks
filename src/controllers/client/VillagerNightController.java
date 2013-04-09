package controllers.client;

import channels.SocketChannel;
import controllers.Phase;
import controllers.Workflow;
import controllers.server.Player;
import messages.PlayerVotedMessage;
import view.client.VillagerNightView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class VillagerNightController {
    protected final SocketChannel channel;
    private final Workflow workflow;
    protected List<Player> players;
    protected VillagerNightView view;
    private Player currentPlayer;
    Timer timer;

    public VillagerNightController(Workflow workflow, SocketChannel channel, List<Player> players, Player currentPlayer) {
        this.workflow = workflow;
        this.channel = channel;
        this.players = players;
        this.currentPlayer = currentPlayer;
    }

    public void bind(VillagerNightView view) {
        this.view = view;
    }

    public void start() {
        view.display();
    }

    public void disconnectingFromServer() {
        channel.stop();
        workflow.getGameDetails();
    }

    public void goToHome() {
        workflow.goToHome();
    }

    public List<Player> getMafiaList() {
        List<Player> mafiaList = new ArrayList<Player>();
        for (Player player : players) {
            if (player.isMafia())
                mafiaList.add(player);
        }
        return mafiaList;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getCurrentPlayerName() {
        return currentPlayer.getName();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    protected void startVoting() {
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
                            channel.send(new PlayerVotedMessage(currentPlayer.getName(), view.getSelectedPlayer(players), players, getMafiaList().size(), Phase.Night));
                        }
                    }
                });
                timer.start();
            }
        };
        EventQueue.invokeLater(runner);
    }
}
