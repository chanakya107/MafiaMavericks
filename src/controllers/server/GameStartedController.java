package controllers.server;

import channels.messages.ChannelMessage;
import channels.server.SocketServer;
import controllers.Workflow;
import messages.ServerDisconnectedMessage;
import view.server.GameStartedView;

import java.util.List;

public class GameStartedController {
    private Workflow workflow;
    private final SocketServer server;
    private final List<Client> players;
    private GameStartedView view;

    public GameStartedController(Workflow workflow, SocketServer server, List<Client> players) {
        this.workflow = workflow;
        this.server = server;
        this.players = players;
    }

    public void bind(GameStartedView view) {
        this.view = view;
    }

    public void start() {
        view.displayPlayers(players);
    }

    public void stopServer() {
        sendMessage(new ServerDisconnectedMessage());
        server.stop();
        workflow.goToHome();
    }

    private void sendMessage(ChannelMessage message) {
        for (Client player : players) {
            player.sendMessage(message);
        }
    }

}
