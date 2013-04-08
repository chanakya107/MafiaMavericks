package messages;

import channels.messages.ChannelMessage;
import controllers.server.Role;

public class GameOverMessage extends ChannelMessage {
    private Role winner;

    public GameOverMessage(Role winner) {
        this.winner = winner;
    }

    public Role getWinner() {
        return winner;
    }
}
