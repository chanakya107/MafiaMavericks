package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;

public class MafiaNightController extends VillagerNightController {
    private final Workflow workflow;
    private final SocketChannel channel;
    private final String serverName;

    public MafiaNightController(Workflow workflow, SocketChannel channel, String serverName) {
        super(workflow, channel, serverName);
        this.workflow = workflow;
        this.channel = channel;
        this.serverName = serverName;
    }

    public void start() {
    }
}
