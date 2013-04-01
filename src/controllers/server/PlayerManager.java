package controllers.server;

public interface PlayerManager {

    void playersJoined(Player player);

    void playerDisconnected(Player player);
}
