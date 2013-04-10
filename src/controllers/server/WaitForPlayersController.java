package controllers.server;

import channels.ConnectionListener;
import channels.SocketChannel;
import channels.messages.ChannelMessage;
import controllers.ConnectionFactory;
import controllers.GameLog;
import controllers.Phase;
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
        if (!connectionFactory.startServer())
            workflow.goToHomeOnError("Server already started");
    }

    public void bind(WaitForPlayersView view) {
        this.view = view;
    }

    public void startGame() {
        connectionFactory.stopServer();
        new RoleAssignment(getPlayers()).assign();
        GameLog.add("Game Started");
        sendNightStartedMessage();
        workflow.startGame(connectionFactory.getServer(), clients);
    }

    private List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Client client : clients) {
            players.add(client.getPlayer());
        }
        return players;
    }

    public void stopServer() {
        sendMessage(new ServerDisconnectedMessage());
        connectionFactory.stopServer();
        workflow.goToHome();
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        Client newClient = new Client(channel, this);
        clients.add(newClient);


        if (clients.size() >= 3) {
            view.enableStartButton();
        }
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
    }

    @Override
    public void playerJoined() {
        view.updatePlayers(clients);
        sendMessage(new PlayersUpdateMessage(getPlayerNames()));
    }

    @Override
    public void playerDisconnected(Client client) {
        clients.remove(client);
        view.updatePlayers(clients);
        sendMessage(new PlayersUpdateMessage(getPlayerNames()));
    }

    @Override
    public void playerKilled(Player playerKilled, Phase phase) {
        String name = playerKilled.getName();
        for (Client client : clients) {
            if (client.getPlayer().equals(playerKilled)) {
                client.sendMessage(new YouAreKilledMessage(client.getPlayer().getName(), GameLog.getLog()));
                client.getPlayer().assignRole(Role.Killed);
                GameLog.add(playerKilled.getName() + " is Dead");
                workflow.startGame(connectionFactory.getServer(), clients);
            }
        }

        if (new RoleAssignment(getPlayers()).canGameContinue()) {
            continueGame(name, phase);
        } else {
            sendGameOverMessage();
            GameLog.add((new RoleAssignment(getPlayers()).getWinner()) + "s won the game");
            workflow.startGame(connectionFactory.getServer(), clients);
        }
    }

    private void sendNightStartedMessage() {
        GameLog.add("Night Started");
        workflow.startGame(connectionFactory.getServer(), clients);
        for (Client client : getRemainingClients()) {
            if (client.getPlayer().isMafia())
                client.sendMessage(new NightStartedMessage(Role.Mafia, getRemainingPlayers(), client.getPlayer(), GameLog.getClientLog()));
            else
                client.sendMessage(new NightStartedMessage(Role.Villager, getRemainingPlayers(), client.getPlayer(), GameLog.getClientLog()));
        }
    }

    private void sendDayStartedMessage(String playerKilled) {
        GameLog.add("Day Started");
        workflow.startGame(connectionFactory.getServer(), clients);
        for (Client client : getRemainingClients()) {
            client.sendMessage(new DayStartedMessage(playerKilled, getRemainingPlayers(), client.getPlayer(), GameLog.getClientLog()));
        }
    }

    private void sendGameOverMessage() {
        GameLog.add("Game Over");
        workflow.startGame(connectionFactory.getServer(), clients);
        for (Client client : clients) {
            client.sendMessage(new GameOverMessage(new RoleAssignment(getPlayers()).getWinner(), GameLog.getLog()));
        }
    }

    private void continueGame(String playerKilled, Phase phase) {
        if (!phase.isNight()) {
            sendNightStartedMessage();
        } else
            sendDayStartedMessage(playerKilled);
    }

    private List<Client> getRemainingClients() {
        List<Client> remainingClients = new ArrayList<Client>();
        for (Client client : clients) {
            if (!client.getPlayer().isKilled())
                remainingClients.add(client);
        }
        return remainingClients;
    }

    private List<Player> getRemainingPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Client client : clients) {
            if (client.getPlayer().getRole() != Role.Killed)
                players.add(client.getPlayer());
        }
        return players;
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
