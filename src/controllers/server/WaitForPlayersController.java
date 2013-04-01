package controllers.server;

import channels.ConnectionListener;
import channels.SocketChannel;
import channels.messages.ChannelMessage;
import controllers.ConnectionFactory;
import controllers.Workflow;
import messages.PlayersUpdateMessage;
import messages.RoleAssignedMessage;
import messages.ServerDisconnectedMessage;
import view.server.WaitForPlayersView;

public class WaitForPlayersController implements PlayerManager, ConnectionListener {
    private final ConnectionFactory connectionFactory;
    private Workflow workflow;
    private WaitForPlayersView view;


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
        new RoleAssignment().assign();
        sendRoleMessage();
        workflow.startGame(connectionFactory.getServer());
    }

    private void sendRoleMessage() {
        for (Player player : players) {
            if (player.getRole() == Role.Mafia)
                player.sendMessage(new RoleAssignedMessage(Role.Mafia));
            else
                player.sendMessage(new RoleAssignedMessage(Role.Villager));
        }
    }

    public void stopServer() {
        sendMessage(new ServerDisconnectedMessage());
        connectionFactory.stopServer();
        workflow.goToHome();
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
        view.updatePlayers(players);
        sendMessage(new PlayersUpdateMessage(getPlayerNames()));
    }

    @Override
    public void playerDisconnected(Player player) {
        players.remove(player);
        view.updatePlayers(players);
        sendMessage(new PlayersUpdateMessage(getPlayerNames()));
    }

    private void sendMessage(ChannelMessage message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    private String getPlayerNames() {
        String resultName = "";
        for (Player player : players) {
            resultName += player.getName() + "\n";
        }
        return resultName;
    }

}
