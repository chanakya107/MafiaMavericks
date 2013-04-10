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
    private final List<String> log;
    private DayView view;

    public DayController(Workflow workflow, String killedPlayer, List<Player> players, Player currentPlayer, SocketChannel channel, List<String> log) {
        this.workflow = workflow;
        this.killedPlayer = killedPlayer;
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.channel = channel;
        this.log = log;
    }

    public void start() {
        view.display();
        view.displayLog(log);
    }

    public void bind(DayView view) {
        this.view = view;
    }

    public void startVoting() {
        view.disableConfirm();
        channel.send(new PlayerVotedMessage(getCurrentPlayer().getName(), getSelectedPlayer(), players, players.size(), Phase.Day));
    }

    private Player getSelectedPlayer() {
        Player playerSelected = null;
        for (Player player : players) {
            if (String.valueOf(player).equals(view.getSelectedPlayer())) {
                playerSelected = player;
            }
        }
        return playerSelected;
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
