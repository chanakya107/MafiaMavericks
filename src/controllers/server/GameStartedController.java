package controllers.server;

import channels.messages.ChannelMessage;
import channels.server.SocketServer;
import controllers.Workflow;
import messages.ServerDisconnectedMessage;
import view.server.GameStartedView;

public class GameStartedController implements PlayerManager {
    private Workflow workflow;
    private final SocketServer server;
    private GameStartedView view;

    public GameStartedController(Workflow workflow, SocketServer server) {
        this.workflow = workflow;
        this.server = server;
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
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    @Override
    public void playerJoined(Player player) {
    }

    @Override
    public void playerDisconnected(Player player) {
    }
}
