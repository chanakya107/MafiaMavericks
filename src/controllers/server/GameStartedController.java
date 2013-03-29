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
    private final List<Player> players;
    private GameStartedView view;

    public GameStartedController(Workflow workflow, SocketServer server, List<Player> players) {
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
        workflow.goBackToHome();
    }

    private void sendMessage(ChannelMessage message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

}
