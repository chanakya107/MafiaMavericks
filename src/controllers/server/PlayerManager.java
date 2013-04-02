package controllers.server;

public interface PlayerManager {

    void playerJoined(ConnectionManager player);

    void playerDisconnected(ConnectionManager player);
}
