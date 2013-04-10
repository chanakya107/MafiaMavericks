package controllers;

import controllers.server.Role;
import view.GameOverView;

import java.util.List;

public class GameOverController {
    private final Workflow workflow;
    private final Role winner;
    private final List<String> log;
    private GameOverView view;

    public GameOverController(Workflow workflow, Role winner, List<String> log) {
        this.workflow = workflow;
        this.winner = winner;
        this.log = log;
    }

    public void bind(GameOverView view) {
        this.view = view;
    }

    public void start() {
        view.displayWinner(winner);
        view.displayLog(log);
    }

    public void goToHome() {
        workflow.goToHome();
    }
}
