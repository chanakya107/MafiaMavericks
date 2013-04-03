package messages;

import channels.messages.ChannelMessage;
import controllers.server.Player;

public class NightStartedMessage extends ChannelMessage {
    private Player[] players;

    public NightStartedMessage(Player[] players) {

        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }
}
