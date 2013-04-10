package view.server;

import controllers.client.Client;

import java.util.List;

public interface GameStartedView {
    void displayPlayers(List<Client> players);

    void displayMessage(String message);
}
