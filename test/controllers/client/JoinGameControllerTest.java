package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import org.junit.Before;
import org.junit.Test;
import view.client.JoinGameView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JoinGameControllerTest {
    private Workflow workflow;
    private JoinGameView view;
    private SocketChannel channel;
    private JoinGameController controller;

    @Before
    public void setup() {
        workflow = mock(Workflow.class);
        view = mock(JoinGameView.class);
        channel = mock(SocketChannel.class);
        controller = new JoinGameController(workflow, channel, "localhost", "player");
        controller.bind(view);
    }

    @Test
    public void channel_binding_is_done_when_a_new_JoinGameController_is_created() {
        verify(channel).bind(controller);
    }

    @Test
    public void start_on_controller_displays_connected_to_server_screen() {
        controller.start();
        verify(view).connectedToServer("localhost", "player");
    }

    @Test
    public void disconnecting_from_server_stops_the_channel_goes_back_to_the_GameDetailsScreen() {
        controller.disconnectingFromServer();
        verify(channel).stop();
        verify(workflow).getGameDetails();
    }
}
