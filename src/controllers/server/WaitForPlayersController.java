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
    private List<Client> clients = new ArrayList<Client>();


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
        new RoleAssignment(getPlayers()).assign();
        sendRoleMessage(clients);
        startNight();
        workflow.startGame(connectionFactory.getServer(), clients);
    }

    private void startNight() {
        Runnable runner = new Runnable() {
            public void run() {
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sendMessage(new NightStartedMessage(getPlayers()));
                        ((Timer) e.getSource()).stop();
                    }
                });
                timer.start();
            }
        };
        EventQueue.invokeLater(runner);
    }

    private List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Client client : clients) {
            players.add(client.getPlayer());
        }
        return players;
    }

    private void sendRoleMessage(List<Client> players) {
        for (Client client : players) {
            if (client.getPlayer().isMafia())
                client.sendMessage(new RoleAssignedMessage(Role.Mafia));
            else
                client.sendMessage(new RoleAssignedMessage(Role.Villager));
        }
    }

    public void stopServer() {
        sendMessage(new ServerDisconnectedMessage());
        connectionFactory.stopServer();
        workflow.goToHome();
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        clients.add(new Client(channel, this));
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {

    }

    @Override
    public void playerJoined(Client player) {
        view.updatePlayers(clients);
        sendMessage(new PlayersUpdateMessage(getPlayerNames()));
    }

    @Override
    public void playerDisconnected(Client player) {
        clients.remove(player);
        view.updatePlayers(clients);
        sendMessage(new PlayersUpdateMessage(getPlayerNames()));
    }

    private void sendMessage(ChannelMessage message) {
        for (Client player : clients) {
            player.sendMessage(message);
        }
    }

    private String getPlayerNames() {
        String resultName = "";
        for (Client client : clients) {
            resultName += client.getPlayer().getName() + "\n";
        }
        return resultName;
    }

}
