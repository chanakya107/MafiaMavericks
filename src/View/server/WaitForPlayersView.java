package view.server;

import controllers.server.Client;

import java.util.List;

public interface WaitForPlayersView {

    void updatePlayers(List<Client> playerList);
}
