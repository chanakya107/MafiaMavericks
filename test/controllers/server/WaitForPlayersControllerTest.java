package controllers.server;

import controllers.Workflow;
import org.junit.Test;
import view.server.WaitForPlayersView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WaitForPlayersControllerTest {
    @Test
    public void players_disconnected_is_successful() {
        Workflow workflow = mock(Workflow.class);
        WaitForPlayersView view = mock(WaitForPlayersView.class);
        WaitForPlayersController controller = new WaitForPlayersController(workflow);
        controller.bind(view);
        Player player = mock(Player.class);
        when(player.getName()).thenReturn("player");
        controller.playerDisconnected(player);
    }
}
