package view.client;

public interface JoinGameView {
    void displayPlayers(String[] playersConnected);

    void connectedToServer(String serverName, String playerName);

    void serverDisconnected(String serverName);

    void goToMafiaScreen();

    void goToVillagerScreen();
}
