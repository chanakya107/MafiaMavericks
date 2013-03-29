package view.server;

import controllers.server.Player;

import java.util.List;

public interface GameStartedView {
    void displayPlayers(List<Player> players);
}
