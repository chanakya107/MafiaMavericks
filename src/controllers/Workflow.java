package controllers;

import channels.SocketChannel;
import channels.server.SocketServer;
import controllers.server.Player;

import java.util.List;

public interface Workflow {
    void startServer();

    void getGameDetails();

    void startGame(SocketServer server, List<Player> players);

    void goToHome();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);

    void mafiaScreen(SocketChannel channel, String serverName);

    void VillagerScreen(SocketChannel channel, String serverName);

    void goToNight();
}
