package view.server;

import controllers.server.Player;

import java.util.ArrayList;

public interface WaitForPlayersView {

    void addPlayers(ArrayList<Player> playerList);

    void removePlayer(ArrayList<Player> players, String name);
}
