package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import controllers.server.Player;

import java.util.List;

public class MafiaNightController extends VillagerNightController {
    public MafiaNightController(Workflow workflow, SocketChannel channel, List<Player> players, Player currentPlayer) {
        super(workflow, channel, players, currentPlayer);
    }

    public void start() {
        view.displayAtNight();
    }
}
