package controllers.server;

import java.util.List;
import java.util.Random;

public class RoleAssignment {
    private List<Player> players;

    public RoleAssignment(List<Player> players) {
        this.players = players;
    }

    public void assign() {
        Random random = new Random();
        int villagerCount = villagerCount();
        int mafiaCount = players.size() - villagerCount();
        if (random.nextBoolean()) {
            assignVillager(villagerCount);
        } else {
            assignMafia(mafiaCount);
        }
    }

    private void assignMafia(int mafiaCount) {
        for (Player player : players) {
            if (mafiaCount > 0) {
                player.assignRole(Role.Mafia);
                mafiaCount--;
            } else
                player.assignRole(Role.Villager);
        }
    }

    private void assignVillager(int villagerCount) {
        for (Player player : players) {
            if (villagerCount > 0) {
                player.assignRole(Role.Villager);
                villagerCount--;
            } else
                player.assignRole(Role.Mafia);
        }
    }

    private int villagerCount() {
        int count = players.size() / 2 + 1;
        return count;
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
}
