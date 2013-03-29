package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import messages.ServerDisconnectedMessage;
import org.junit.Before;
import org.junit.Test;
import view.client.VillagerView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class VillagerControllerTest {
    Workflow workflow;
    SocketChannel channel;
    VillagerController controller;

    @Before
    public void setup() {
        workflow = mock(Workflow.class);
        channel = mock(SocketChannel.class);
        controller = new VillagerController(workflow, channel, "localhost");
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

    @Test
    public void on_new_ServerDisconnectedMessage_arrived_it_calls_serverDisconnected_and_stops_the_channel() {
        ServerDisconnectedMessage serverDisconnectedMessage = mock(ServerDisconnectedMessage.class);
        VillagerView view = mock(VillagerView.class);
        controller.bind(view);
        controller.onNewMessageArrived(channel, serverDisconnectedMessage);
        verify(view).serverDisconnected("localhost");
        verify(channel).stop();
    }

}
