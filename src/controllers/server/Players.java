package controllers.server;

import channels.ConnectionListener;
import channels.SocketChannel;
import channels.messages.ChannelMessage;
import channels.server.SocketServer;
import controllers.Workflow;
import messages.PlayerConnectedMessage;
import messages.PlayerDisconnectedMessage;

import java.util.ArrayList;
import java.util.List;

public class Players implements ConnectionListener, God {
    private final SocketServer server = new SocketServer(1254, this);
    private final List<Player> players = new ArrayList<Player>();
    private final Workflow workflow;

    public List<Player> getPlayers() {
        return players;
    }

    public SocketServer getServer() {
        return server;
    }

    public Players(Workflow workflow) {
        this.workflow = workflow;
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        players.add(new Player(channel, this));
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
    }

    @Override
    public void playersJoined(Player player) {
        sendMessage(new PlayerConnectedMessage(getPlayerNames()));
        workflow.updatePlayersList(players);
    }

    @Override
    public void playerDisconnected(Player player) {
        players.remove(player);
        sendMessage(new PlayerDisconnectedMessage(getPlayerNames()));
        workflow.updatePlayersList(players);
    }

    private String getPlayerNames() {
        String resultName = "";
        for (Player player : players) {
            resultName += player.getName() + "\n";
        }
        return resultName;
    }

    private void sendMessage(ChannelMessage message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }
}
