package messages;

import channels.messages.ChannelMessage;
import controllers.server.Player;

import java.util.List;

public class PlayerVotedMessage extends ChannelMessage {
    private final String playerName;
    private final Player votedTo;
    private List<Player> players;
    private int votersCount;

    public PlayerVotedMessage(String playerName, Player votedTo, List<Player> players, int votersCount) {
        this.playerName = playerName;
        this.votedTo = votedTo;
        this.players = players;
        this.votersCount = votersCount;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player getVotedTo() {
        return votedTo;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getVotersCount() {
        return votersCount;
    }
}
