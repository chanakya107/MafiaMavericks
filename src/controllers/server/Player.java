package controllers.server;

public class Player {
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
}
