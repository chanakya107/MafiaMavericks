package view.server;

import controllers.server.ConnectionManager;

import java.util.List;

public interface WaitForPlayersView {

    void updatePlayers(List<ConnectionManager> playerList);
}
