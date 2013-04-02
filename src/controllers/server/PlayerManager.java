package controllers.server;

public interface PlayerManager {

    void playerJoined(Player player);

    void playerDisconnected(Player player);
}
