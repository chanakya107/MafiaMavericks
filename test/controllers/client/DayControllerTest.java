package controllers.client;

import controllers.Workflow;
import controllers.server.Player;
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
    public void on_day_started_the_player_killed_in_the_night_should_be_displayed_and_the_voting_should_be_displayed() {
        controller.start();
        verify(view).displayKilledPlayer();
        verify(view).displayVoting();
    }
}
