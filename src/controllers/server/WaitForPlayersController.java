package controllers.server;

import channels.ConnectionListener;
import channels.SocketChannel;
import channels.messages.ChannelMessage;
import controllers.ConnectionFactory;
import controllers.Workflow;
import controllers.client.Client;
import messages.*;
import view.server.WaitForPlayersView;

import java.util.ArrayList;
import java.util.List;

public class WaitForPlayersController implements PlayerManager, ConnectionListener {
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
        if (new RoleAssignment(getPlayers()).gameCanContinue()){
            sendDayStartedMessage(clients);
        }
        workflow.startGame(connectionFactory.getServer(), clients);
    }

    private void sendDayStartedMessage(List<Client> clients) {

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

    @Override
    public void playerKilled(Player playerKilled)
    {
        informKilledPlayer(playerKilled);
//        informOtherPlayers(playerKilled);
    }

    public void informOtherPlayers(Player playerKilled) {
        for (Client client : clients) {
            client.sendMessage(new PlayerKilledMessage(playerKilled));
        }
    }

    public void informKilledPlayer(Player playerKilled) {
        for (Client client : clients) {
            if (client.getPlayer().equals(playerKilled)) {
                client.sendMessage(new YouAreKilledMessage());
//                todo : chethan - try to stop and remove the client from the list here.
//                client.stop();
//                clients.remove(client);
            }
            else
                client.sendMessage(new PlayerKilledMessage(playerKilled));
        }
    }

    private void sendMessage(ChannelMessage message) {
        for (Client client : clients) {
            client.sendMessage(message);
        }
    }

    private String getPlayerNames() {
        String resultName = "";
        for (Client client : clients) {
            resultName += client.getPlayer().getName() + "\n";
        }
        return resultName;
    }

}
