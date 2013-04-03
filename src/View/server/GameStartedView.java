package view.server;

import controllers.server.Client;

import java.util.List;

public interface GameStartedView {
    void displayPlayers(List<Client> players);
}
