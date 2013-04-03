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
        return this.role == Role.Mafia;
    }
}
