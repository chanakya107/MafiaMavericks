package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import controllers.server.Player;
import org.junit.Before;
import org.junit.Test;
import view.client.VillagerNightView;

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
        controller = new VillagerNightController(workflow, channel, new ArrayList<Player>(), new Player("player"));
    }

    @Test
    public void on_start_display_of_night_screen_should_be_made() {
        VillagerNightView view = mock(VillagerNightView.class);
        controller.bind(view);
        controller.start();
        verify(view).display();
    }

    @Test
    public void disconnecting_from_server_will_stop_the_channel_and_goes_back_to_the_gameDetailsScreen() {
        controller.disconnectingFromServer();
        verify(channel).stop();
        verify(workflow).goToHome();
    }

    @Test
    public void goToHome_will_display_homeScreen_through_workflow() {
        controller.goToHome();
        verify(workflow).goToHome();
    }
}
