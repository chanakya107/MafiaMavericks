package controllers;

import channels.SocketChannel;

public interface Workflow {
    void startServer();

    void getGameDetails();

    void startGame();

    void goBackToHome();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);
}
