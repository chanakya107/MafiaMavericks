package messages;

import channels.messages.ChannelMessage;
import controllers.server.Player;

import java.util.List;

public class DayStartedMessage extends ChannelMessage {
    private String killedPlayer;
    private List<Player> playersRemaining;
    private Player currentPlayer;

    public DayStartedMessage(String killedPlayer, List<Player> playersRemaining, Player currentPlayer) {
        this.killedPlayer = killedPlayer;
        this.playersRemaining = playersRemaining;
        this.currentPlayer = currentPlayer;
    }

    public String getKilledPlayer() {
        return killedPlayer;
    }

    public List<Player> getPlayersRemaining() {
        return playersRemaining;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
