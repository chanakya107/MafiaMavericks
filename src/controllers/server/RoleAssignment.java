package controllers.server;

import java.util.List;
import java.util.Random;

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
        if(random.nextBoolean())
            return Role.Villager;
        return Role.Mafia;
    }
}
