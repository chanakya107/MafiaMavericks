package controllers.client;

import controllers.Workflow;
import controllers.server.Player;
import view.client.PlayerKilledView;

public class PlayerKilledController {
    private PlayerKilledView view;
    private Workflow workflow;
    private Player killedPlayer;

    public PlayerKilledController(Workflow workflow, Player killedPlayer) {
        this.workflow = workflow;
        this.killedPlayer = killedPlayer;
    }

    public void bind(PlayerKilledView view) {

        this.view = view;
    }

    public void start() {
        view.displayKilledPlayer(killedPlayer);
    }
}
