package controllers.server;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private Role role;

    public Player(String playerName) {
        name = playerName;
    }

    public void assignRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public boolean isMafia() {
        return role == Role.Mafia;
    }

    public boolean isVillager() {
        return role == Role.Villager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return !(name != null ? !name.equals(player.name) : player.name != null) && role == player.role;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
