package controllers;

import channels.SocketChannel;
import channels.server.SocketServer;
import controllers.client.Client;
import controllers.server.Player;
import controllers.server.Role;

import java.util.List;

public interface Workflow {
    void startServer();

    void getGameDetails();

    void goToHome();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);

    void mafiaNightScreen(SocketChannel channel, String serverName, List<Player> players, Player currentPlayer, List<String> log);

    void villagerNightScreen(SocketChannel channel, String serverName, List<Player> players, Player currentPlayer, List<String> log);

    void startGame(SocketServer server, List<Client> players);

    void dayStarted(String killedPlayer, List<Player> playersRemaining, Player currentPlayer, SocketChannel channel, List<String> log);

    void YouAreKilled(String name, List<String> log);

    void gameOver(Role winner, List<String> log);

    void goToHomeOnError(String message);
}
