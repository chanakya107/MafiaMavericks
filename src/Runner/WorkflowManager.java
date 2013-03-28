package runner;

import channels.SocketChannel;
import controllers.ConnectionFactory;
import controllers.HomeController;
import controllers.Workflow;
import controllers.client.ClientDetailsController;
import controllers.client.JoinGameController;
import controllers.client.WelcomeController;
import controllers.server.GameStartedController;
import controllers.server.Player;
import controllers.server.Players;
import controllers.server.WaitForPlayersController;
import screens.HomeScreen;
import screens.client.ClientDetailsScreen;
import screens.client.JoinGameScreen;
import screens.client.WelcomeScreen;
import screens.controls.MainFrame;
import screens.server.GameStartedScreen;
import screens.server.WaitForPlayersScreen;

import java.util.List;

public class WorkflowManager implements Workflow {
    private MainFrame mainFrame;
    private Players players;

    public void start() {
        mainFrame = new MainFrame();
        players = new Players(this);
        HomeController controller = new HomeController(this);
        controller.bind(new HomeScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void startServer() {
        WaitForPlayersController controller = new WaitForPlayersController(this,players.getServer(),players.getPlayers());
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
    public void updatePlayersList(List<Player> playerList) {
        WaitForPlayersController controller = new WaitForPlayersController(this, players.getServer(), players.getPlayers());
        new WaitForPlayersScreen(mainFrame, controller).updatePlayers(playerList);
    }

}
