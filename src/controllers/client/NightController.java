package controllers.client;

import controllers.Workflow;
import controllers.server.Player;
import controllers.server.Role;
import view.client.NightView;

import java.util.ArrayList;
import java.util.List;

public class NightController {
    private final Workflow workflow;
    private Player[] players;
    private NightView view;

    public NightController(Workflow workflow, Player[] players) {
        this.workflow = workflow;
        this.players = players;
    }

    public void bind(NightView view) {

        this.view = view;
    }

    public void start() {
        view.displayMafia(getMafiaList());
    }

    private List<Player> getMafiaList() {
        List<Player> mafiaList = new ArrayList<Player>();
        for (Player player : players) {
            if (player.isMafia())
                mafiaList.add(player);
        }
        return mafiaList;
    }
}
