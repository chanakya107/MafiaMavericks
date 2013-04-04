package controllers.server;

import controllers.client.Client;

public interface PlayerManager {

    void playerJoined(Client player);

    void playerDisconnected(Client player);
}
