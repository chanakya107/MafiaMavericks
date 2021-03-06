package controllers.client;

import channels.SocketChannel;
import channels.SocketChannelListener;
import channels.messages.ChannelMessage;
import controllers.GameLog;
import controllers.server.Player;
import controllers.server.PlayerManager;
import messages.PlayerDetailsMessage;
import messages.PlayerVotedMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client implements SocketChannelListener {

    private static final List<Player> playersSelected = new ArrayList<Player>();
    private final SocketChannel channel;
    private PlayerManager playerManager;
    private Player player;


    public Client(SocketChannel channel, PlayerManager playerManager) {
        this.channel = channel;
        this.playerManager = playerManager;
        channel.bind(this);
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
        channel.stop();
        playerManager.playerDisconnected(this);
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        if (message instanceof PlayerDetailsMessage) {
            PlayerDetailsMessage playerDetailsMessage = (PlayerDetailsMessage) message;
            player = playerDetailsMessage.getPlayer();
            player.assignNumber(hashCode());
            playerManager.playerJoined();
        } else if (message instanceof PlayerVotedMessage) {
            PlayerVotedMessage playerVotedMessage = (PlayerVotedMessage) message;
            playersSelected.add(playerVotedMessage.getVotedTo());
            GameLog.add(playerVotedMessage.getPlayerName() + " voted to " + playerVotedMessage.getVotedTo().getName());
            if (hasEveryoneVoted(playerVotedMessage.getVotersCount())) {
                playerManager.playerKilled(getPlayerToBeKilled(), playerVotedMessage.getPhase());
            }
        }
    }

    private Player getPlayerToBeKilled() {
        int votes = 0;
        Player playerToBeKilled = null;
        for (Player player : playersSelected) {
            int temp = Collections.frequency(playersSelected, player);
            if (temp > votes) {
                votes = temp;
                playerToBeKilled = player;
            }
        }
        playersSelected.clear();
        return playerToBeKilled;
    }

    private boolean hasEveryoneVoted(int votersCount) {
        return playersSelected.size() == votersCount;
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
    }

    public void sendMessage(ChannelMessage message) {
        channel.send(message);
    }

    public Player getPlayer() {
        return player;
    }

    public void stop() {
        channel.stop();
    }
}
