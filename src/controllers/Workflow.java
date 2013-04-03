package controllers;

import channels.SocketChannel;
import channels.server.SocketServer;
import controllers.server.Client;
import controllers.server.Player;

import java.util.List;

public interface Workflow {
    void startServer();

    void getGameDetails();

    void goToHome();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);

    void mafiaNightScreen(SocketChannel channel, String serverName);

    void villagerNightScreen(SocketChannel channel, String serverName);

    void goToNight(List<Player> players);

    void startGame(SocketServer server, List<Client> players);
}
