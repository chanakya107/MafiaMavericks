package messages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class PlayerDisconnectedMessage extends ChannelMessage implements Serializable {
    private final String[] playerNameList;

    public PlayerDisconnectedMessage(String playerNames) {
        super();
        this.playerNameList = playerNames.split("\n");
    }

    public String[] getPlayersConnected() {
        return playerNameList;
    }

}
