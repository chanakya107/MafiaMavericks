package controllers;

import channels.SocketChannel;
import channels.server.SocketServer;
import controllers.server.Player;

import java.util.ArrayList;

public interface Workflow {
    void startServer();

    void getGameDetails();

    void startGame(SocketServer server, ArrayList<Player> players);

    void goBackToHome();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);

    void welcomePlayers(SocketChannel channel, String serverName);

}
