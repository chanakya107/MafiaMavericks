package view.server;

import controllers.server.ConnectionManager;

import java.util.List;

public interface GameStartedView {
    void displayPlayers(List<ConnectionManager> players);
}
