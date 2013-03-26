package screens;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class PlayerConnectedMessage extends ChannelMessage implements Serializable {
    private final String[] playerName;

    public PlayerConnectedMessage(String playerNames) {
        super();
        this.playerName = playerNames.split("\n");
    }

    public String[] getPlayersConnected() {
        return playerName;
    }

}
