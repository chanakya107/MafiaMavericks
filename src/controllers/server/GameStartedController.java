package controllers.server;

import channels.messages.ChannelMessage;
import channels.server.SocketServer;
import controllers.Workflow;
import controllers.client.Client;
import messages.ServerDisconnectedMessage;
import view.server.GameStartedView;

import java.util.List;

public class GameStartedController {
    private Workflow workflow;
    private final SocketServer server;
    private final List<Client> clients;
    private GameStartedView view;

    public GameStartedController(Workflow workflow, SocketServer server, List<Client> players) {
        this.workflow = workflow;
        this.server = server;
        this.clients = players;
    }

    public void bind(GameStartedView view) {
        this.view = view;
    }

    public void start() {
        view.displayPlayers(clients);
        view.displayLog();
    }

    public void stopServer() {
        sendMessage(new ServerDisconnectedMessage());
        server.stop();
        workflow.goToHome();
    }

    private void sendMessage(ChannelMessage message) {
        for (Client client : clients) {
            client.sendMessage(message);
        }
    }

}
