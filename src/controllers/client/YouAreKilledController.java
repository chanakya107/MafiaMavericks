package controllers.client;

import controllers.Workflow;
import view.client.YouAreKilledView;

public class YouAreKilledController {
    private Workflow workflow;
    private String name;
    private YouAreKilledView view;

    public YouAreKilledController(Workflow workflow, String name) {
        this.workflow = workflow;
        this.name = name;
    }

    public void bind(YouAreKilledView view) {
        this.view = view;
    }

    public void start() {

    }

    public void goToHome() {
        workflow.goToHome();
    }

    public String getName() {
        return name;
    }
}
