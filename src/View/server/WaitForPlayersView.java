package view.server;

import controllers.server.Player;

import java.util.ArrayList;
import java.util.List;

public interface WaitForPlayersView {

void updatePlayers(List<Player> playerList);
}
