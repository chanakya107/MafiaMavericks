package controllers.server;

import channels.SocketChannel;
import channels.SocketChannelListener;
import channels.messages.ChannelMessage;
import messages.PlayerDetailsMessage;

import java.io.IOException;

public class Client implements SocketChannelListener {

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
        playerManager.playerDisconnected(this);
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        if (message instanceof PlayerDetailsMessage) {
            PlayerDetailsMessage playerDetailsMessage = (PlayerDetailsMessage) message;
            player = new Player(playerDetailsMessage.getPlayerName());
            playerManager.playerJoined(this);
        }
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
}
