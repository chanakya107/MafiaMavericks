package screens;

import view.JoinGameView;

public class JoinGameController {
    private Workflow workflow;
    private JoinGameView view;

    public JoinGameController(Workflow workflow) {

        this.workflow = workflow;
    }

    public void bind(JoinGameView view) {
        this.view = view;
    }
}
