package runner;

import channels.SocketChannel;
import channels.server.SocketServer;
import controllers.ConnectionFactory;
import controllers.HomeController;
import controllers.Workflow;
import controllers.client.ClientDetailsController;
import controllers.client.JoinGameController;
import controllers.client.MafiaController;
import controllers.client.VillagerController;
import controllers.server.GameStartedController;
import controllers.server.Player;
import controllers.server.WaitForPlayersController;
import screens.HomeScreen;
import screens.client.ClientDetailsScreen;
import screens.client.JoinGameScreen;
import screens.client.MafiaScreen;
import screens.client.VillagerScreen;import screens.controls.MainFrame;
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
    public void startGame(SocketServer server, List<Player> players) {
        GameStartedController controller = new GameStartedController(this,server,players);
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
    public void MafiaScreen(SocketChannel channel, String serverName) {
        MafiaController controller = new MafiaController(this, channel, serverName);
        controller.bind(new MafiaScreen(mainFrame, controller));
        controller.start();
    }

    @Override
    public void VillagerScreen(SocketChannel channel, String serverName) {
        VillagerController controller = new VillagerController(this,channel,serverName);
        controller.bind(new VillagerScreen(mainFrame,controller));
        controller.start();
    }
}
