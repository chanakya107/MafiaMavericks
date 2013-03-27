package view;

import controllers.server.Player;

import java.util.ArrayList;

public interface StartGameView {

    void addPlayers(ArrayList<Player> playerList);

    void removePlayer(ArrayList<Player> players, String name);
}
