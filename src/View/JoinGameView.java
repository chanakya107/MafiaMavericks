package view;

public interface JoinGameView {
    void displayConnectedPlayers(String[] playersConnected);

    void connectedToServer(String serverName, String playerName);
}
