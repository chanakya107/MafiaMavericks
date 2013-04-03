package controllers.client;

import controllers.Workflow;
import controllers.server.Player;
import view.client.NightView;

public class NightController {
    private final Workflow workflow;
    private Player[] players;
    private NightView view;

    public NightController(Workflow workflow, Player[] players) {
        this.workflow = workflow;
        this.players = players;
    }

    public void bind(NightView view) {

        this.view = view;
    }

    public void start() {
    }
}
