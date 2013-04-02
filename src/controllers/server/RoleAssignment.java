package controllers.server;

import java.util.List;
import java.util.Random;

import static java.lang.StrictMath.floor;

public class RoleAssignment {
    private List<ConnectionManager> players;

    public RoleAssignment(List<ConnectionManager> players) {
        this.players = players;
    }

    public void assign() {
        for (ConnectionManager connectionManager : players) {
            connectionManager.getPlayer().assignRole(getRole());
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
        for (ConnectionManager connectionManager : players) {
            if (connectionManager.getPlayer().getRole() == Role.Mafia)
                mafiaCount++;
        }
        return mafiaCount < floor(players.size() / 2);
    }
}
