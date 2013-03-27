package controllers.client;

import controllers.Workflow;
import view.WelcomeView;

public class WelcomeController {
    private Workflow workflow;
    private WelcomeView view;

    public WelcomeController(Workflow workflow) {
        this.workflow = workflow;
    }

    public void bind(WelcomeView view) {
        this.view = view;
    }

    public void start() {

    }
}
