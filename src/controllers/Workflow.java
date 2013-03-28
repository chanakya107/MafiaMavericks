package controllers;

import channels.SocketChannel;
import channels.server.SocketServer;
import controllers.server.Player;

import java.util.ArrayList;
import java.util.List;

public interface Workflow {
    void startServer();

    void getGameDetails();

    void startGame();

    void goBackToHome();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);

    void welcomePlayers(SocketChannel channel, String serverName);

    void updatePlayersList(List<Player> players);
}
