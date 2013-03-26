package screens;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class PlayerDetailsMessage extends ChannelMessage implements Serializable {

    private final String playerName;

    public PlayerDetailsMessage(String playerName) {
        super();
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

}
