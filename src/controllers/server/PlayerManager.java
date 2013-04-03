package controllers.server;

public interface PlayerManager {

    void playerJoined(Client player);

    void playerDisconnected(Client player);
}
