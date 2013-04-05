package messages;

import channels.messages.ChannelMessage;
import controllers.server.Player;

public class PlayerVotedMessage extends ChannelMessage {
    private final String playerName;
    private final Player votedTo;

    public PlayerVotedMessage(String playerName, Player votedTo) {
        this.playerName = playerName;
        this.votedTo = votedTo;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player getVotedTo() {
        return votedTo;
    }
}
