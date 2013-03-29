package messages;

import channels.messages.ChannelMessage;

public class PlayerDetailsMessage extends ChannelMessage {

    private final String playerName;

    public PlayerDetailsMessage(String playerName) {
        super();
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

}
