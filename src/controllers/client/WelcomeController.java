package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import view.client.WelcomeView;

public class WelcomeController {
    private Workflow workflow;
    private SocketChannel channel;
    private WelcomeView view;

    public WelcomeController(Workflow workflow, SocketChannel channel) {
        this.workflow = workflow;
        this.channel = channel;
    }

    public void bind(WelcomeView view) {

        this.view = view;
    }

    public void start() {

    }

    public void disconnectingFromServer() {
        channel.stop();
        workflow.getGameDetails();
    }
}
