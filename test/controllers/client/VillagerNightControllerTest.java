package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import controllers.server.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class VillagerNightControllerTest {
    Workflow workflow;
    SocketChannel channel;
    VillagerNightController controller;

    @Before
    public void setup() {
        workflow = mock(Workflow.class);
        channel = mock(SocketChannel.class);
        controller = new VillagerNightController(workflow, channel, new ArrayList<Player>());
    }

    @Test
    public void disconnecting_from_server_will_stop_the_channel_and_goes_back_to_the_gameDetailsScreen() {
        controller.disconnectingFromServer();
        verify(channel).stop();
        verify(workflow).getGameDetails();
    }

    @Test
    public void goToHome_will_display_homeScreen_through_workflow() {
        controller.goToHome();
        verify(workflow).goToHome();
    }

}
