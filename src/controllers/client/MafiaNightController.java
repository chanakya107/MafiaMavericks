package controllers.client;

import channels.SocketChannel;
import controllers.Phase;
import controllers.Workflow;
import controllers.server.Player;
import messages.PlayerVotedMessage;

import java.util.List;

public class MafiaNightController extends VillagerNightController {

    public MafiaNightController(Workflow workflow, SocketChannel channel, List<Player> players, Player currentPlayer) {
        super(workflow, channel, players, currentPlayer);
    }

    public void start() {
        view.display();
    }


    public void startVoting() {
        view.disableConfirm();
        channel.send(new PlayerVotedMessage(getCurrentPlayer().getName(), getSelectedPlayer(), players, getMafiaList().size(), Phase.Night));
    }

    public void voteChanged() {
        channel.send(new PlayerVotedMessage(getCurrentPlayer().getName(), getSelectedPlayer(), players, getMafiaList().size(), Phase.Night));
    }

    private Player getSelectedPlayer() {
        Player playerSelected = null;
        for (Player player : players) {
            if (String.valueOf(player).equals(view.getSelectedPlayer())) {
                playerSelected = player;
            }
        }
        return playerSelected;
    }
}
