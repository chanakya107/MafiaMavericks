package controllers.server;

import java.util.ArrayList;
import java.util.List;

public interface PlayerManager {

    List<Player> players = new ArrayList<Player>();

    void playersJoined(Player player);

    void playerDisconnected(Player player);
}
