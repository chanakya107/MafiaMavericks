package runner;

import channels.SocketChannel;
import channels.server.SocketServer;
import controllers.ConnectionFactory;
import controllers.GameOverController;
import controllers.HomeController;
import controllers.Workflow;
import controllers.client.*;
import controllers.server.GameStartedController;
import controllers.server.Player;
import controllers.server.Role;
import controllers.server.WaitForPlayersController;
import screens.GameOverScreen;
import screens.HomeScreen;
import screens.client.*;
import screens.controls.MainFrame;
import screens.server.GameStartedScreen;
import screens.server.WaitForPlayersScreen;

import java.util.List;

public class WorkflowManager implements Workflow {
    private MainFrame mainFrame;

    public void start() {
        mainFrame = new MainFrame();
        HomeController controller = new HomeController(this);
        controller.bind(new HomeScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void startServer() {
        WaitForPlayersController controller = new WaitForPlayersController(this, new ConnectionFactory());
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
    public void goToHome() {
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
    public void mafiaNightScreen(SocketChannel channel, String serverName, List<Player> players, Player currentPlayer) {
        MafiaNightController controller = new MafiaNightController(this, channel, players, currentPlayer);
        controller.bind(new MafiaNightScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void villagerNightScreen(SocketChannel channel, String serverName, List<Player> players, Player currentPlayer) {
        VillagerNightController controller = new VillagerNightController(this, channel, players, currentPlayer);
        controller.bind(new VillagerNightScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void dayStarted(String killedPlayer, List<Player> playersRemaining, Player currentPlayer, SocketChannel channel) {
        DayController controller = new DayController(this, killedPlayer, playersRemaining, currentPlayer, channel);
        controller.bind(new DayScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void startGame(SocketServer server, List<Client> players) {
        GameStartedController controller = new GameStartedController(this, server, players);
        controller.bind(new GameStartedScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void YouAreKilled() {
        YouAreKilledController controller = new YouAreKilledController(this);
        controller.bind(new YouAreKilledScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void gameOver(Role winner) {
        GameOverController controller = new GameOverController(this, winner);
        controller.bind(new GameOverScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void goToHomeOnError(String message) {
        HomeController controller = new HomeController(this);
        controller.bind(new HomeScreen(mainFrame, controller));
        controller.displayError(message);
    }
}
