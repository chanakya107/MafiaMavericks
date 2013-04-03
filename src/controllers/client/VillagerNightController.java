package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import controllers.server.Player;
import view.client.VillagerNightView;

import java.util.ArrayList;
import java.util.List;

public class VillagerNightController {
    private final Workflow workflow;
    private final SocketChannel channel;
    protected List<Player> players;
    protected VillagerNightView view;

    public VillagerNightController(Workflow workflow, SocketChannel channel, List<Player> players) {
        this.workflow = workflow;
        this.channel = channel;
        this.players = players;
    }

    public void bind(VillagerNightView view) {
        this.view = view;
    }

    public void start() {
        view.displayAtNight(getMafiaList());
    }

    public void disconnectingFromServer() {
        channel.stop();
        workflow.getGameDetails();
    }

    public void goToHome() {
        workflow.goToHome();
    }

    protected List<Player> getMafiaList() {
        List<Player> mafiaList = new ArrayList<Player>();
        for (Player player : players) {
            if (player.isMafia())
                mafiaList.add(player);
        }
        return mafiaList;
    }
}
