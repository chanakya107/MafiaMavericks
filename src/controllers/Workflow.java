package controllers;

import channels.SocketChannel;
import channels.server.SocketServer;
import controllers.client.Client;
import controllers.server.Player;

import java.util.List;

public interface Workflow {
    void startServer();

    void getGameDetails();

    void goToHome();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);

    void mafiaNightScreen(SocketChannel channel, String serverName, List<Player> players, Player currentPlayer);

    void villagerNightScreen(SocketChannel channel, String serverName, List<Player> players, Player currentPlayer);

    void startGame(SocketServer server, List<Client> players);

    void dayStarted(String killedPlayer, List<Player> playersRemaining);
}
