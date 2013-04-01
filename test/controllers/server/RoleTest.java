package controllers.server;

import org.junit.Test;
import view.client.JoinGameView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoleTest {
    @Test
    public void goToScreen_on_mafia_should_goto_mafia_screen() {
        JoinGameView view = mock(JoinGameView.class);
        Role.Mafia.goToScreen(view);
        verify(view).goToMafiaScreen();
    }

    @Test
    public void goToScreen_on_villager_should_goto_villager_screen() {
        JoinGameView view = mock(JoinGameView.class);
        Role.Villager.goToScreen(view);
        verify(view).goToVillagerScreen();
    }
}
