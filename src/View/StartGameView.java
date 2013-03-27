package view;

import controllers.server.Player;

import java.util.ArrayList;

public interface StartGameView {

    void addPlayers(ArrayList<Player> playerList);

    void removePlayer(Player player);
}
