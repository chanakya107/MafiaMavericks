package view.client;

import controllers.server.Player;

import java.util.List;

public interface VillagerNightView {
    void display();

    Player getSelectedPlayer(List<Player> players);

    void disableConfirm();
}
