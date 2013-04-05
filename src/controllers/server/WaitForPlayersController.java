package controllers.server;

import channels.ConnectionListener;
import channels.SocketChannel;
import channels.SocketChannelListener;
import channels.messages.ChannelMessage;
import controllers.ConnectionFactory;
import controllers.Workflow;
import controllers.client.Client;
import messages.NightStartedMessage;
import messages.PlayersUpdateMessage;
import messages.ServerDisconnectedMessage;
import view.server.WaitForPlayersView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WaitForPlayersController implements PlayerManager, ConnectionListener, SocketChannelListener {
    private final ConnectionFactory connectionFactory;
    private Workflow workflow;
    private WaitForPlayersView view;
    private List<Client> clients = new ArrayList<Client>();


    public WaitForPlayersController(Workflow workflow, ConnectionFactory connectionFactory) {
        this.workflow = workflow;
        this.connectionFactory = connectionFactory;
    }

    public void start() {
        connectionFactory.createServer(this);
        connectionFactory.startServer();
    }

    public void bind(WaitForPlayersView view) {
        this.view = view;
    }

    public void startGame() {
        new RoleAssignment(getPlayers()).assign();
        sendNightStartedMessage(clients);
        workflow.startGame(connectionFactory.getServer(), clients);
    }

    private List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Client client : clients) {
            players.add(client.getPlayer());
        }
        return players;
    }

    private void sendNightStartedMessage(List<Client> players) {
        for (Client client : players) {
            if (client.getPlayer().isMafia())
                client.sendMessage(new NightStartedMessage(Role.Mafia, getPlayers(), client.getPlayer()));
            else
                client.sendMessage(new NightStartedMessage(Role.Villager, getPlayers(), client.getPlayer()));
        }
    }

    public void stopServer() {
        sendMessage(new ServerDisconnectedMessage());
        connectionFactory.stopServer();
        workflow.goToHome();
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        clients.add(new Client(channel, this));
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {

    }

    @Override
    public void playerJoined(Client player) {
        view.updatePlayers(clients);
        sendMessage(new PlayersUpdateMessage(getPlayerNames()));
    }

    @Override
    public void playerDisconnected(Client player) {
        clients.remove(player);
        view.updatePlayers(clients);
        sendMessage(new PlayersUpdateMessage(getPlayerNames()));
    }

    private void sendMessage(ChannelMessage message) {
        for (Client player : clients) {
            player.sendMessage(message);
        }
    }

    private String getPlayerNames() {
        String resultName = "";
        for (Client client : clients) {
            resultName += client.getPlayer().getName() + "\n";
        }
        return resultName;
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
    }
}
