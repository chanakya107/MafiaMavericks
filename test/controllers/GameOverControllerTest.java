package controllers;

import controllers.server.Role;
import org.junit.Test;
import view.GameOverView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameOverControllerTest {
    @Test
    public void on_start_the_winner_should_be_displayed(){
        Workflow workflow = mock(Workflow.class);
        GameOverController controller = new GameOverController(workflow, Role.Mafia);
        GameOverView view = mock(GameOverView.class);
        controller.bind(view);
        controller.start();
        verify(view).displayWinner(Role.Mafia);
    }
}
