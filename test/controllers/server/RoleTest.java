package controllers.server;

import channels.SocketChannel;
import controllers.Workflow;
import org.junit.Test;

import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoleTest {
    @Test
    public void goToScreen_on_mafia_should_goto_mafia_screen() {
        Workflow workflow = mock(Workflow.class);
        SocketChannel channel = new SocketChannel(new Socket());
        Role.Mafia.goToScreen(workflow, channel, "localhost");
        verify(workflow).mafiaNightScreen(channel, "localhost");
    }

    @Test
    public void goToScreen_on_villager_should_goto_villager_screen() {
        Workflow workflow = mock(Workflow.class);
        SocketChannel channel = new SocketChannel(new Socket());
        Role.Villager.goToScreen(workflow, channel, "localhost");
        verify(workflow).villagerNightScreen(channel, "localhost");
    }
}
