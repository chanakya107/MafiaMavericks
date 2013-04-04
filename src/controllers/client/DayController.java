package controllers.client;

import controllers.Workflow;
import controllers.server.Player;
import controllers.server.Role;
import controllers.server.RoleAssignment;
import view.client.DayView;

import java.util.List;

public class DayController {
    private final Workflow workflow;
    private final String killedPlayer;
    private final List<Player> playersRemaining;
    private DayView view;

    public DayController(Workflow workflow, String killedPlayer, List<Player> playersRemaining) {

        this.workflow = workflow;
        this.killedPlayer = killedPlayer;
        this.playersRemaining = playersRemaining;
    }

    public void start() {
        view.displayKilledPlayer();
        if (gameCanContinue())
            view.displayVoting();
        else
            view.displayWinner(decideWinner(playersRemaining));
    }

    private Role decideWinner(List<Player> playersRemaining) {
        RoleAssignment roleAssignment = new RoleAssignment(playersRemaining);
        if (roleAssignment.getMafiaCount() == roleAssignment.getVillagerCount()) return Role.Mafia;
        else if (roleAssignment.getMafiaCount() == 0) return Role.Villager;
        return null;
    }

    public boolean gameCanContinue() {
        return new RoleAssignment(playersRemaining).checkRatio();
    }

    public void bind(DayView view) {
        this.view = view;
    }

    public String getKilledPlayer() {
        return killedPlayer;
    }
}
