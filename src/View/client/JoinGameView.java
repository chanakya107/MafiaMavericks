package view.client;

public interface JoinGameView {
    void displayConnectedPlayers(String[] playersConnected);

    void connectedToServer(String serverName, String playerName);

    void serverDisconnected(String serverName);

    void gameStarted();

}
