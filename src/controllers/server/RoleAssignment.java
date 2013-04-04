package controllers.server;

import java.util.List;
import java.util.Random;

import static java.lang.StrictMath.floor;

public class RoleAssignment {
    private List<Player> players;

    public RoleAssignment(List<Player> players) {
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
        return getMafiaCount() < floor(players.size() / 2);
    }

    public int getMafiaCount() {
        int mafiaCount = 0;
        for (Player player : players) {
            if (player.isMafia())
                mafiaCount++;
        }
        return mafiaCount;
    }

    public int getVillagerCount() {
        int villagerCount = 0;
        for (Player player : players) {
            if (player.isVillager())
                villagerCount++;
        }
        return villagerCount;
    }

}
