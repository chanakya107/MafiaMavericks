package view.client;

import controllers.server.Role;

public interface DayView {
    void displayKilledPlayer();

    void displayVoting();

    void displayWinner(Role role);
}
