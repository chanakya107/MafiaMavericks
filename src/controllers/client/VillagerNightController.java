package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import controllers.server.Player;
import view.client.VillagerNightView;

import java.util.ArrayList;
import java.util.List;


public class VillagerNightController {
    protected final SocketChannel channel;
    private final Workflow workflow;
    protected List<Player> players;
    protected VillagerNightView view;
    private Player currentPlayer;
    protected final List<String> log;

    public VillagerNightController(Workflow workflow, SocketChannel channel, List<Player> players, Player currentPlayer, List<String> log) {
        this.workflow = workflow;
        this.channel = channel;
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.log = log;
    }

    public void bind(VillagerNightView view) {
        this.view = view;
    }

    public void start() {
        view.display();
    }

    public void disconnectingFromServer() {
        channel.stop();
        workflow.goToHome();
    }

    public void goToHome() {
        workflow.goToHome();
    }

    public List<Player> getMafiaList() {
        List<Player> mafiaList = new ArrayList<Player>();
        for (Player player : players) {
            if (player.isMafia())
                mafiaList.add(player);
        }
        return mafiaList;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
