package messages;

import channels.messages.ChannelMessage;
import controllers.server.Player;

import java.util.List;

public class VotingCompletedMessage extends ChannelMessage {
    private List<Player> playersSelected;

    public VotingCompletedMessage(List<Player> playersSelected) {
        this.playersSelected = playersSelected;
    }

    public List<Player> getPlayersSelected() {
        return playersSelected;
    }
}
