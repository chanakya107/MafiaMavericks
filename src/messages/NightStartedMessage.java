package messages;

import channels.messages.ChannelMessage;
import controllers.server.Player;

import java.util.List;

public class NightStartedMessage extends ChannelMessage {
    private List<Player> players;

    public NightStartedMessage(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
