package runner;

import channels.ConnectionListener;
import channels.SocketChannel;
import channels.messages.ChannelMessage;
import channels.server.SocketServer;
import controllers.ConnectionFactory;
import controllers.HomeController;
import controllers.Workflow;
import controllers.client.ClientDetailsController;
import controllers.client.JoinGameController;
import controllers.client.WelcomeController;
import controllers.server.GameStartedController;
import controllers.server.God;
import controllers.server.Player;
import controllers.server.WaitForPlayersController;
import messages.PlayerConnectedMessage;
import messages.PlayerDisconnectedMessage;
import screens.HomeScreen;
import screens.client.ClientDetailsScreen;
import screens.client.JoinGameScreen;
import screens.client.WelcomeScreen;
import screens.controls.MainFrame;
import screens.server.GameStartedScreen;
import screens.server.WaitForPlayersScreen;

import java.util.ArrayList;

public class WorkflowManager implements Workflow, ConnectionListener, God {
    private MainFrame mainFrame;
    private final SocketServer server = new SocketServer(1254, this);
    private final ArrayList<Player> players = new ArrayList<Player>();

    public void start() {
        mainFrame = new MainFrame();
        HomeController controller = new HomeController(this);
        controller.bind(new HomeScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void startServer() {
        WaitForPlayersController controller = new WaitForPlayersController(this);
        controller.bind(new WaitForPlayersScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void getGameDetails() {
        ClientDetailsController controller = new ClientDetailsController(this, new ConnectionFactory());
        controller.bind(new ClientDetailsScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void startGame() {
        GameStartedController controller = new GameStartedController(this);
        controller.bind(new GameStartedScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void goBackToHome() {
        HomeController controller = new HomeController(this);
        controller.bind(new HomeScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void connectedToServer(SocketChannel channel, String serverName, String playerName) {
        JoinGameController controller = new JoinGameController(this, channel, serverName, playerName);
        controller.bind(new JoinGameScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void welcomePlayers(SocketChannel channel, String serverName) {
        WelcomeController controller = new WelcomeController(this, channel, serverName);
        controller.bind(new WelcomeScreen(mainFrame, controller));
        controller.start();
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
        updatePlayersList();
    }

    private void updatePlayersList() {
        WaitForPlayersController controller = new WaitForPlayersController(this);
        new WaitForPlayersScreen(mainFrame, controller).updatePlayers(players);
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

    @Override
    public void playerDisconnected(Player player) {
        players.remove(player);
        sendMessage(new PlayerDisconnectedMessage(getPlayerNames()));
        updatePlayersList();
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public SocketServer getServer() {
        return server;
    }

}
