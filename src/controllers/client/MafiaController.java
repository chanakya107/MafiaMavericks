package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;

public class MafiaController extends VillagerController {
    private final Workflow workflow;
    private final SocketChannel channel;
    private final String serverName;

    public MafiaController(Workflow workflow, SocketChannel channel, String serverName) {
        super(workflow, channel, serverName);
        this.workflow = workflow;
        this.channel = channel;
        this.serverName = serverName;
    }

    public void goToNight() {
        workflow.goToNight(serverName,channel);
    }
}
