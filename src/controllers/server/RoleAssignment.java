package controllers.server;

import java.util.Random;

import static java.lang.StrictMath.floor;

public class RoleAssignment {
    private Player[] players;

    public RoleAssignment(Player[] players) {
        this.players = players;
    }

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
            if (player.isMafia())
                mafiaCount++;
        }
        return mafiaCount < floor(players.length / 2);
    }
}
