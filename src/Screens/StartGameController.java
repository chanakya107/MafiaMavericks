package screens;

import channels.messages.ChannelMessage;
import messages.PlayerConnectedMessage;
import channels.server.SocketServer;
import channels.server.SocketServerListener;
import channels.SocketChannel;
import view.StartGameView;

import java.util.ArrayList;

public class StartGameController implements SocketServerListener, God {
    private Workflow workflow;
    private StartGameView view;
    SocketServer server = new SocketServer(1254,this);
    private ArrayList<Player> players = new ArrayList<Player>();

    public StartGameController(Workflow workflow) {

        this.workflow = workflow;
    }

    public void start() {
        server.start();
    }

    public void bind(StartGameView view) {
        this.view = view;
    }

    public void startGame() {
        workflow.startGame();
    }

    public void stopServer() {
        sendMessage(new ServerDisconnectedMessage());
        server.stop();
        workflow.goBackToHome();
    }

    @Override
    public void onError(Exception e) {
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        players.add(new Player(channel, this));
    }

    @Override
    public void playersUpdated(Player player) {
        view.updatePlayers(players);
        sendMessage(new PlayerConnectedMessage(getPlayerNames()));
    }



    private void sendMessage(ChannelMessage message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    private String getPlayerNames() {
        String resultName = "";
        for (Player player : players) {
            resultName += player.getName() + "\n";
        }
        return resultName;
    }
}
