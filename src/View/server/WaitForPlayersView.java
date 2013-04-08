package view.server;

import controllers.client.Client;

import java.util.List;

public interface WaitForPlayersView {

    void updatePlayers(List<Client> playerList);

    void displayStartButton();
}
