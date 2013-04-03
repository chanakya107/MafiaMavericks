package messages;

import channels.messages.ChannelMessage;
import controllers.server.Player;
import controllers.server.Role;

import java.util.List;

public class RoundStartedMessage extends ChannelMessage {
    private Role role;
    private List<Player> players;

    public RoundStartedMessage(Role role, List<Player> players) {
        this.role = role;
        this.players = players;
    }

    public Role getRole() {
        return role;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
