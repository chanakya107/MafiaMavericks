package view.client;

import controllers.server.Player;

public interface VillagerNightView {
    void displayAtNight();

    void displayTimer(int count);

    Player getSelectedPlayer();
}
