package messages;

import channels.messages.ChannelMessage;

import java.io.Serializable;

public class PlayerConnectedMessage extends ChannelMessage implements Serializable {
    private final String[] playerNameList;

    public PlayerConnectedMessage(String playerNames) {
        super();
        this.playerNameList = playerNames.split("\n");
    }

    public String[] getPlayersConnected() {
        return playerNameList;
    }

}
