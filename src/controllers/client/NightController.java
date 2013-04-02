package controllers.client;

import controllers.Workflow;
import view.client.NightView;

public class NightController {
    private final Workflow workflow;
    private NightView view;

    public NightController(Workflow workflow) {
        this.workflow = workflow;
    }

    public void bind(NightView view) {

        this.view = view;
    }

    public void start() {

    }
}
