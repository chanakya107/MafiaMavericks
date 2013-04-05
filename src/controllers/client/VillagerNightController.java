package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import controllers.server.Player;
import view.client.VillagerNightView;

import java.util.ArrayList;
import java.util.List;


public class VillagerNightController {
    private final Workflow workflow;
    protected final SocketChannel channel;
    protected List<Player> players;
    protected VillagerNightView view;
    private Player currentPlayer;

    public VillagerNightController(Workflow workflow, SocketChannel channel, List<Player> players, Player currentPlayer) {
        this.workflow = workflow;
        this.channel = channel;
        this.players = players;
        this.currentPlayer = currentPlayer;
    }

    public void bind(VillagerNightView view) {
        this.view = view;
    }

    public void start() {

        view.displayAtNight();
    }

    public void disconnectingFromServer() {
        channel.stop();
        workflow.getGameDetails();
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

    public String getCurrentPlayer() {
        return currentPlayer.getName();
    }


}
