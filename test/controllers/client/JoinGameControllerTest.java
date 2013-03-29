package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import messages.PlayersUpdateMessage;
import org.junit.Before;
import org.junit.Test;
import view.client.JoinGameView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    public void goToHome_displays_the_homeScreen_through_workflow(){
        controller.goToHome();
        verify(workflow).goToHome();
    }

    @Test
    public void goToMafiaScreen_displays_mafia_screen_though_workflow(){
        controller.goToMafiaScreen();
        verify(workflow).mafiaScreen(channel,"localhost");
    }

    @Test
    public void goToVillagerScreen_displays_Villager_screen_through_workflow(){
        controller.goToVillagerScreen();
        verify(workflow).VillagerScreen(channel,"localhost");
    }

    @Test
    public void on_new_PlayersUpdatedMessage_arrived_will_display_the_players_updated(){
        PlayersUpdateMessage playersUpdateMessage = mock(PlayersUpdateMessage.class);
        String[] players = {"player"};
        when(playersUpdateMessage.getPlayersConnected()).thenReturn(players);
        controller.onNewMessageArrived(channel,playersUpdateMessage);
        verify(view).displayPlayers(players);
    }

}
