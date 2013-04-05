package messages;

import channels.messages.ChannelMessage;
import controllers.server.Player;

public class PlayerKilledMessage extends ChannelMessage {
    private Player killedPlayer;

    public PlayerKilledMessage(Player killedPlayer) {
        this.killedPlayer = killedPlayer;
    }

    public Player getKilledPlayer() {
        return killedPlayer;
    }
}
