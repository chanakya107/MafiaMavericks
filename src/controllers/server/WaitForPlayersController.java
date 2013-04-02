package controllers.server;

import channels.ConnectionListener;
import channels.SocketChannel;
import channels.messages.ChannelMessage;
import controllers.ConnectionFactory;
import controllers.Workflow;
import messages.NightStartedMessage;
import messages.PlayersUpdateMessage;
import messages.RoleAssignedMessage;
import messages.ServerDisconnectedMessage;
import view.server.WaitForPlayersView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WaitForPlayersController implements PlayerManager, ConnectionListener {
    private final ConnectionFactory connectionFactory;
    private Workflow workflow;
    private WaitForPlayersView view;
    private List<ConnectionManager> players = new ArrayList<ConnectionManager>();


    public WaitForPlayersController(Workflow workflow, ConnectionFactory connectionFactory) {
        this.workflow = workflow;
        this.connectionFactory = connectionFactory;
    }

    public void start() {
        connectionFactory.createServer(this);
        connectionFactory.startServer();
    }

    public void bind(WaitForPlayersView view) {
        this.view = view;
    }

    public void startGame() {
        new RoleAssignment(players).assign();
        sendRoleMessage(players);
        startNight();
        workflow.startGame(connectionFactory.getServer(), players);
    }

    private void startNight() {
        Runnable runner = new Runnable() {
            public void run() {
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sendMessage(new NightStartedMessage());

                    }
                });
                timer.start();
            }
        };
        EventQueue.invokeLater(runner);
    }

    private void sendRoleMessage(List<ConnectionManager> players) {
        for (ConnectionManager connectionManager : players) {
            if (connectionManager.getPlayer().getRole() == Role.Mafia)
                connectionManager.sendMessage(new RoleAssignedMessage(Role.Mafia));
            else
                connectionManager.sendMessage(new RoleAssignedMessage(Role.Villager));
        }
    }

    public void stopServer() {
        sendMessage(new ServerDisconnectedMessage());
        connectionFactory.stopServer();
        workflow.goToHome();
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        players.add(new ConnectionManager(channel, this));
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {

    }

    @Override
    public void playerJoined(ConnectionManager player) {
        view.updatePlayers(players);
        sendMessage(new PlayersUpdateMessage(getPlayerNames()));
    }

    @Override
    public void playerDisconnected(ConnectionManager player) {
        players.remove(player);
        view.updatePlayers(players);
        sendMessage(new PlayersUpdateMessage(getPlayerNames()));
    }

    private void sendMessage(ChannelMessage message) {
        for (ConnectionManager player : players) {
            player.sendMessage(message);
        }
    }

    private String getPlayerNames() {
        String resultName = "";
        for (ConnectionManager connectionManager : players) {
            resultName += connectionManager.getPlayer().getName() + "\n";
        }
        return resultName;
    }

}
