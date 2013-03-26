package screens;

import channels.Messages.ChannelMessage;
import channels.SocketChannel;
import channels.SocketChannelListener;
import view.JoinGameView;

import java.io.IOException;

public class JoinGameController implements SocketChannelListener {
    private Workflow workflow;
    private final SocketChannel channel;
    private final String serverName;
    private final String playerName;
    private JoinGameView view;

    public JoinGameController(Workflow workflow, SocketChannel channel, String serverName, String playerName) {

        this.workflow = workflow;
        this.channel = channel;
        this.serverName = serverName;
        this.playerName = playerName;
        channel.bind(this);
    }

    public void bind(JoinGameView view) {
        this.view = view;
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
        throw new RuntimeException("connection closed",e);
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        if (message instanceof PlayerConnectedMessage) {
            PlayerConnectedMessage playerConnectedMessage = (PlayerConnectedMessage) message;
            view.displayConnectedPlayers(playerConnectedMessage.getPlayersConnected());
        }
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
    }

    public void start() {

    }
}
