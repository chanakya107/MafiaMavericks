package controllers;

import controllers.server.Role;
import view.GameOverView;

public class GameOverController {
    private final Workflow workflow;
    private final Role winner;
    private GameOverView view;

    public GameOverController(Workflow workflow, Role winner) {
        this.workflow = workflow;
        this.winner = winner;
    }

    public void bind(GameOverView view) {
        this.view = view;
    }

    public void start() {
        view.displayWinner(winner);
    }

    public void goToHome() {
        workflow.goToHome();
    }
}
