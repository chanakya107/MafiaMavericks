package controllers.client;

import channels.SocketChannel;
import channels.SocketChannelListener;
import channels.messages.ChannelMessage;
import controllers.Workflow;
import controllers.server.Player;
import messages.*;
import view.client.JoinGameView;

import java.io.IOException;

public class JoinGameController implements SocketChannelListener {
    private final SocketChannel channel;
    private final String serverName;
    private final String playerName;
    private Workflow workflow;
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

    public void start() {
        view.connectedToServer(serverName, playerName);
        channel.send(new PlayerDetailsMessage(new Player(playerName)));
    }

    public void disconnectingFromServer() {
        channel.stop();
        workflow.getGameDetails();
    }

    public void goToHome() {
        workflow.goToHome();
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        if (message instanceof PlayersUpdateMessage) {
            PlayersUpdateMessage playersUpdateMessage = (PlayersUpdateMessage) message;
            view.displayPlayers(playersUpdateMessage.getPlayersConnected());
        } else if (message instanceof ServerDisconnectedMessage) {
            view.serverDisconnected("Connection to server : " + serverName + " is lost");
            channel.stop();
        } else if (message instanceof NightStartedMessage) {
            NightStartedMessage nightStartedMessage = (NightStartedMessage) message;
            nightStartedMessage.getRole().goToScreen(workflow, channel, serverName, nightStartedMessage.getPlayers(), nightStartedMessage.getPlayer(),nightStartedMessage.getLog());
        } else if (message instanceof DayStartedMessage) {
            DayStartedMessage dayStartedMessage = (DayStartedMessage) message;
            workflow.dayStarted(dayStartedMessage.getKilledPlayer(), dayStartedMessage.getPlayersRemaining(), dayStartedMessage.getCurrentPlayer(), channel,dayStartedMessage.getLog());
        } else if (message instanceof YouAreKilledMessage) {
            YouAreKilledMessage youAreKilledMessage = (YouAreKilledMessage) message;
            workflow.YouAreKilled(youAreKilledMessage.getName(),youAreKilledMessage.getLog());
        } else if (message instanceof GameOverMessage) {
            GameOverMessage gameOverMessage = (GameOverMessage) message;
            workflow.gameOver(gameOverMessage.getWinner(),gameOverMessage.getLog());
        }
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
    }

    public void serverDisconnected(String message) {
        workflow.goToHomeOnError(message);
    }
}
