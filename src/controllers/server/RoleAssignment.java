package controllers.server;

import java.util.List;
import java.util.Random;

public class RoleAssignment {
    private List<Player> players;

    public RoleAssignment(List<Player> players) {
        this.players = players;
    }

    public void assign() {
        int villagerCount = villagerCount();
        int mafiaCount = players.size() - villagerCount();
        if (new Random().nextBoolean()) {
            assignRole(mafiaCount, Role.Mafia, Role.Villager);
        } else {
            assignRole(villagerCount, Role.Villager, Role.Mafia);
        }
    }

    private void assignRole(int Count, Role firstRole, Role secondRole) {
        for (Player player : players) {
            if (Count > 0) {
                player.assignRole(firstRole);
                Count--;
            } else
                player.assignRole(secondRole);
        }
    }

    private int villagerCount() {
        return players.size() / 2 + 1;
    }

    public int getVillagerCount() {
        int villagerCount = 0;
        for (Player player : players) {
            if (player.isVillager())
                villagerCount++;
        }
        return villagerCount;
    }

    public int getMafiaCount() {
        int mafiaCount = 0;
        for (Player player : players) {
            if (player.isMafia())
                mafiaCount++;
        }
        return mafiaCount;
    }

    public boolean gameCanContinue() {
        return getVillagerCount() > getMafiaCount();
    }
}
