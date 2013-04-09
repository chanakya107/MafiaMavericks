package messages;

import channels.messages.ChannelMessage;
import controllers.server.Player;

public class PlayerDetailsMessage extends ChannelMessage {

    private final Player player;

    public PlayerDetailsMessage(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerDetailsMessage)) return false;

        PlayerDetailsMessage message = (PlayerDetailsMessage) o;

        if (player != null ? !player.equals(message.player) : message.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return player != null ? player.hashCode() : 0;
    }
}
