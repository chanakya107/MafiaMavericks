package screens;

import view.HomeView;

public class HomeController {

    private Workflow workflow;
    private HomeView view;

    public HomeController(Workflow workflow) {
        this.workflow = workflow;
    }

    public void startServer() {
        workflow.startServer();
    }

    public void joinGame() {
        workflow.joinServer();
    }

    public void bind(HomeView view) {
        this.view = view;
    }

    public void start() {
    }
}
