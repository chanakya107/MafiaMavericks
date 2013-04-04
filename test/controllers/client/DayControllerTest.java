package controllers.client;

import controllers.Workflow;
import controllers.server.Player;
import controllers.server.Role;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import view.client.DayView;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DayControllerTest {
    private Workflow workflow;
    private DayController controller;
    private DayView view;

    @Before
    public void setup() {
        workflow = mock(Workflow.class);
        controller = new DayController(workflow, "Player", new ArrayList<Player>());
        view = mock(DayView.class);
        controller.bind(view);
    }

    @Test
    public void on_day_started_the_player_killed_in_the_night_should_be_displayed() {
        controller.start();
        verify(view).displayKilledPlayer();
    }

    @Test
    public void game_cannot_continue_if_the_ratio_of_mafia_and_villager_are_equal() {

        ArrayList<Player> playersRemaining = new ArrayList<Player>();
        Player raghav = new Player("raghav");
        raghav.assignRole(Role.Mafia);
        playersRemaining.add(raghav);

        Player chanakya = new Player("chanakya");
        chanakya.assignRole(Role.Villager);
        playersRemaining.add(chanakya);

        DayController dayController = new DayController(workflow, "player", playersRemaining);
        Assert.assertEquals(false, dayController.gameCanContinue());
    }

    @Test
    public void game_can_continue_if_the_ratio_of_mafia_and_villager_is_greater_than_50_percent() {
        ArrayList<Player> playersRemaining = new ArrayList<Player>();
        Player raghav = new Player("raghav");
        raghav.assignRole(Role.Mafia);
        playersRemaining.add(raghav);

        Player chanakya = new Player("chanakya");
        chanakya.assignRole(Role.Villager);
        playersRemaining.add(chanakya);

        Player deepthi = new Player("deepthi");
        deepthi.assignRole(Role.Villager);
        playersRemaining.add(deepthi);

        DayController dayController = new DayController(workflow, "player", playersRemaining);
        Assert.assertEquals(true, dayController.gameCanContinue());
    }


}
