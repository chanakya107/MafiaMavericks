package controllers.server;

import channels.SocketChannel;
import channels.SocketChannelListener;
import channels.messages.ChannelMessage;
import messages.PlayerDetailsMessage;

import java.io.IOException;

public class Player implements SocketChannelListener {

    private final SocketChannel channel;
    private PlayerManager playerManager;
    private String name;
    private Role role;

    public Player(SocketChannel channel, PlayerManager playerManager) {

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
            name = playerDetailsMessage.getPlayerName();
            playerManager.playersJoined(this);
        }
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
    }

    public void sendMessage(ChannelMessage message) {
        channel.send(message);
    }

    public void assignRole(Role role) {
        this.role = role;
    }


    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}
