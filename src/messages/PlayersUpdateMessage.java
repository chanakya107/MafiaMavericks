package messages;

import channels.messages.ChannelMessage;

public class PlayersUpdateMessage extends ChannelMessage {
    private final String[] playerNameList;

    public PlayersUpdateMessage(String playerNames) {
        this.playerNameList = playerNames.split("\n");
    }

    public String[] getPlayersConnected() {
        return playerNameList;
    }

}
