package controllers;

import channels.SocketChannel;
import channels.server.SocketServer;

public interface Workflow {
    void startServer();

    void getGameDetails();

    void startGame(SocketServer server);

    void goToHome();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);

    void mafiaScreen(SocketChannel channel, String serverName);

    void VillagerScreen(SocketChannel channel, String serverName);

    void goToNight(String serverName, SocketChannel channel);
}
