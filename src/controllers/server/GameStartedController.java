package controllers.server;

import controllers.Workflow;
import view.server.GameStartedView;

public class GameStartedController {
    private Workflow workflow;
    private GameStartedView view;

    public GameStartedController(Workflow workflow) {
        this.workflow = workflow;
    }

    public void bind(GameStartedView view) {
        this.view = view;
    }

    public void start() {

    }
}
