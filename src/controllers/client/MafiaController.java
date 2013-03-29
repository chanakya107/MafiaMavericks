package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;

public class MafiaController extends VillagerController {
    public MafiaController(Workflow workflow, SocketChannel channel, String serverName) {
        super(workflow, channel, serverName);
    }
}
