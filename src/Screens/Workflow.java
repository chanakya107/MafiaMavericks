package screens;

import channels.SocketChannel;

public interface Workflow {
    void startServer();

    void getClientDetails();

    void startGame();

    void goBackToHome();

    void connectedToServer(SocketChannel channel, String serverName, String playerName);

}
