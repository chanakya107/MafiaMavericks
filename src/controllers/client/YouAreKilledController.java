package controllers.client;

import controllers.Workflow;
import view.client.YouAreKilledView;

import java.util.List;

public class YouAreKilledController {
    private Workflow workflow;
    private String name;
    private final List<String> log;
    private YouAreKilledView view;

    public YouAreKilledController(Workflow workflow, String name, List<String> log) {
        this.workflow = workflow;
        this.name = name;
        this.log = log;
    }

    public void bind(YouAreKilledView view) {
        this.view = view;
    }

    public void start() {
        view.displayLog(log);
    }

    public void goToHome() {
        workflow.goToHome();
    }

    public String getName() {
        return name;
    }
}
