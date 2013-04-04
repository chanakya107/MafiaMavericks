package messages;

import channels.messages.ChannelMessage;
import controllers.server.Player;

import java.util.List;

public class DayStartedMessage extends ChannelMessage {
    private String killedPlayer;
    private List<Player> playersRemaining;

    public DayStartedMessage(String killedPlayer, List<Player> playersRemaining) {
        this.killedPlayer = killedPlayer;
        this.playersRemaining = playersRemaining;
    }

    public String getKilledPlayer() {
        return killedPlayer;
    }

    public List<Player> getPlayersRemaining() {
        return playersRemaining;
    }
}
