package controllers.server;

import java.util.Random;

import static java.lang.StrictMath.floor;

public class RoleAssignment implements PlayerManager {

    public void assign() {
        for (Player player : players) {
            player.assignRole(getRole());
        }
    }

    private Role getRole() {
        Random random = new Random();
        if (random.nextBoolean() && canAssignToMafia())
            return Role.Mafia;
        return Role.Villager;
    }

    private boolean canAssignToMafia() {
        int mafiaCount = 0;
        for (Player player : players) {
            if (player.getRole() == Role.Mafia)
                mafiaCount++;
        }
        return mafiaCount < floor(players.size() / 2);
    }

    @Override
    public void playersJoined(Player player) {
    }

    @Override
    public void playerDisconnected(Player player) {
    }
}
