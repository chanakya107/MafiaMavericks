package runner;

import channels.SocketChannel;
import controllers.*;
import controllers.client.ClientDetailsController;
import controllers.client.JoinGameController;
import controllers.client.WelcomeController;
import controllers.server.StartGameController;
import screens.*;
import screens.client.ClientDetailsScreen;
import screens.client.JoinGameScreen;
import screens.client.WelcomeScreen;
import screens.controls.MainFrame;
import screens.server.StartGameScreen;

public class WorkflowManager implements Workflow {
    private MainFrame mainFrame;

    public void start() {
        mainFrame = new MainFrame();
        HomeController controller =new HomeController(this);
        controller.bind(new HomeScreen(mainFrame,controller));
        controller.start();
    }

    @Override
    public void startServer() {
        StartGameController controller = new StartGameController(this);
        controller.bind(new StartGameScreen(mainFrame,controller));
        controller.start();
    }

    @Override
    public void getClientDetails() {
        ClientDetailsController controller = new ClientDetailsController(this);
        controller.bind(new ClientDetailsScreen(mainFrame,controller));

    }

    @Override
    public void startGame() {
        WelcomeController controller = new WelcomeController(this);
        controller.bind(new WelcomeScreen(mainFrame,controller));
        controller.start();
    }

    @Override
    public void goBackToHome() {
        HomeController controller = new HomeController(this);
        controller.bind(new HomeScreen(mainFrame,controller));
        controller.start();
    }

    @Override
    public void connectedToServer(SocketChannel channel, String serverName, String playerName) {
        JoinGameController controller = new JoinGameController(this,channel,serverName,playerName);
        controller.bind(new JoinGameScreen(mainFrame,controller));
        controller.start();
    }

}
