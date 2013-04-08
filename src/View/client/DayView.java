package view.client;

import controllers.server.Player;

import java.util.List;

public interface DayView {
    void display();

    void displayTimer(int count);

    Player getSelectedPlayer(List<Player> players);
}
