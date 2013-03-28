package controllers.server;

import channels.messages.ChannelMessage;
import channels.server.SocketServer;
import controllers.Workflow;
import messages.GameStartedMessage;
import messages.ServerDisconnectedMessage;
import view.server.WaitForPlayersView;

import java.util.ArrayList;
import java.util.List;

public class WaitForPlayersController {
    private Workflow workflow;
    private WaitForPlayersView view;
    private SocketServer server;
    private List<Player> players;

    public WaitForPlayersController(Workflow workflow, SocketServer server, List<Player> players) {
        this.workflow = workflow;
        this.server = server;
        this.players = players;
    }

    public void start() {
        server.start();
    }

    public void bind(WaitForPlayersView view) {
        this.view = view;
    }

    public void startGame() {
        sendMessage(new GameStartedMessage());
        workflow.startGame();
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
