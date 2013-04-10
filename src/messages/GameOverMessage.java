package messages;

import channels.messages.ChannelMessage;
import controllers.server.Role;

import java.util.List;

public class GameOverMessage extends ChannelMessage {
    private Role winner;
    private final List<String> log;

    public GameOverMessage(Role winner, List<String> log) {
        this.winner = winner;
        this.log = log;
    }

    public Role getWinner() {
        return winner;
    }

    public List<String> getLog() {
        return log;
    }
}
