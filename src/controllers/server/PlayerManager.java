package controllers.server;

import controllers.Phase;
import controllers.client.Client;

public interface PlayerManager {

    void playerJoined(Client player);

    void playerDisconnected(Client player);

    void playerKilled(Player playerToBeKilled, Phase phase);
}
