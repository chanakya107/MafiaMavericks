package controllers.client;

import channels.SocketChannel;
import controllers.Phase;
import controllers.Workflow;
import controllers.server.Player;
import messages.PlayerVotedMessage;
import view.client.DayView;

import java.util.List;

public class DayController {

    private final Workflow workflow;
    private final String killedPlayer;
    private final List<Player> players;
    private Player currentPlayer;
    private SocketChannel channel;
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
    }

    public void bind(DayView view) {
        this.view = view;
    }

    public void startVoting() {
        view.disableConfirm();
        channel.send(new PlayerVotedMessage(currentPlayer.getName(), view.getSelectedPlayer(players), players, players.size(), Phase.Day));
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

    public void disconnectingFromServer() {
        channel.stop();
        workflow.goToHome();
    }
}
