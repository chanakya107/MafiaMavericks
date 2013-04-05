package controllers.client;

import controllers.Workflow;
import view.client.YouAreKilledView;

public class YouAreKilledController {
    private Workflow workflow;
    private YouAreKilledView view;

    public YouAreKilledController(Workflow workflow) {
        this.workflow = workflow;
    }

    public void bind(YouAreKilledView view) {
        this.view = view;
    }

    public void start() {

    }
}
