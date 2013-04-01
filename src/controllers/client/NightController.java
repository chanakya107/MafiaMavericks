package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import view.client.NightView;

public class NightController {
    private final Workflow workflow;
    private final String serverName;
    private final SocketChannel channel;
    private NightView view;

    public NightController(Workflow workflow, String serverName, SocketChannel channel) {
        this.workflow = workflow;
        this.serverName = serverName;
        this.channel = channel;
    }

    public void bind(NightView view) {

        this.view = view;
    }

    public void start() {

    }
}
