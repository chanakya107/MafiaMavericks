package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import screens.client.VillagerView;

public class VillagerController {
    private final Workflow workflow;
    private final SocketChannel channel;
    private final String serverName;
    private VillagerView view;

    public VillagerController(Workflow workflow, SocketChannel channel, String serverName) {

        this.workflow = workflow;
        this.channel = channel;
        this.serverName = serverName;
    }

    public void bind(VillagerView view) {

        this.view = view;
    }

    public void start() {

    }

    public void disconnectingFromServer() {

    }

    public void goToHome() {

    }
}
