package controllers.server;

import channels.ConnectionListener;
import channels.SocketChannel;
import channels.messages.ChannelMessage;
import channels.server.SocketServer;
import controllers.Workflow;
import messages.PlayerConnectedMessage;
import messages.PlayerDisconnectedMessage;
import messages.RoleAssignedMessage;
import messages.ServerDisconnectedMessage;
import view.server.WaitForPlayersView;

import java.util.ArrayList;
import java.util.List;

public class WaitForPlayersController implements God, ConnectionListener {
    private Workflow workflow;
    private WaitForPlayersView view;
    private List<Player> players = new ArrayList<Player>();
    private SocketServer server = new SocketServer(1254, this);


    public WaitForPlayersController(Workflow workflow) {
        this.workflow = workflow;
    }

    public void start() {
        server.start();
    }

    public void bind(WaitForPlayersView view) {
        this.view = view;
    }

    public void startGame() {
        new RoleAssignment(players).assign();
        sendRoleMessage(players);
        workflow.startGame(server, players);
    }

    private void sendRoleMessage(List<Player> players) {
        for (Player player : players) {
            if (player.getRole() == Role.Mafia)
                player.sendMessage(new RoleAssignedMessage(Role.Mafia));
            else
                player.sendMessage(new RoleAssignedMessage(Role.Villager));
        }
    }

    public void stopServer() {
        sendMessage(new ServerDisconnectedMessage());
        server.stop();
        workflow.goBackToHome();
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
        sendMessage(new PlayerConnectedMessage(getPlayerNames()));
    }

    @Override
    public void playerDisconnected(Player player) {
        players.remove(player);
        view.updatePlayers(players);
        sendMessage(new PlayerDisconnectedMessage(getPlayerNames()));
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
