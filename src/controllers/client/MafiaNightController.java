package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import controllers.server.Player;

import java.util.List;

public class MafiaNightController extends VillagerNightController {
    public MafiaNightController(Workflow workflow, SocketChannel channel, String serverName, List<Player> players) {
        super(workflow, channel, players);
    }

    public void start() {
        view.displayAtNight(getMafiaList());
    }


}
