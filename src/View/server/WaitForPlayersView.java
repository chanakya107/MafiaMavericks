package view.server;

import controllers.server.Player;

import java.util.ArrayList;

public interface WaitForPlayersView {

void updatePlayers(ArrayList<Player> playerList);
}
