package controllers.client;

import controllers.Workflow;
import controllers.server.Player;
import view.client.DayView;

import java.util.List;

public class DayController {
    private final Workflow workflow;
    private final String killedPlayer;
    private final List<Player> playersRemaining;
    private DayView view;
    private List<Player> players;
    private String currentPlayer;

    public DayController(Workflow workflow, String killedPlayer, List<Player> playersRemaining) {

        this.workflow = workflow;
        this.killedPlayer = killedPlayer;
        this.playersRemaining = playersRemaining;
    }

    public void start() {
        view.displayKilledPlayer();
        view.displayVoting();
    }

    public void bind(DayView view) {
        this.view = view;
    }

    public String getKilledPlayer() {
        return killedPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
}
