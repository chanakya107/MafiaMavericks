package controllers.server;

import channels.messages.ChannelMessage;
import controllers.Workflow;
import controllers.server.God;
import controllers.server.Player;
import messages.PlayerConnectedMessage;
import channels.server.SocketServer;
import channels.server.SocketServerListener;
import channels.SocketChannel;
import messages.ServerDisconnectedMessage;
import messages.playerDisconnectedMessage;
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
    public void playersJoined(Player player) {
        view.addPlayers(players);
        sendMessage(new PlayerConnectedMessage(getPlayerNames()));
    }

    @Override
    public void playerDisconnected(Player player) {
//        for(int i = 0; i<players.size();i ++)
//        {
//            if(players..equals(player))
//            {
//                players.remove(i);
//            }
//        }
        players.remove(player);
        view.addPlayers(players);
        sendMessage(new playerDisconnectedMessage());
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
