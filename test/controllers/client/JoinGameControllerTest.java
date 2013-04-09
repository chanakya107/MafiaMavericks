package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import controllers.server.Player;
import controllers.server.Role;
import messages.*;
import org.junit.Before;
import org.junit.Test;
import view.client.JoinGameView;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

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
        verify(channel).send(new PlayerDetailsMessage(new Player("player")));
    }

    @Test
    public void disconnecting_from_server_stops_the_channel_goes_back_to_the_GameDetailsScreen() {
        controller.disconnectingFromServer();
        verify(channel).stop();
        verify(workflow).getGameDetails();
    }

    @Test
    public void goToHome_displays_the_homeScreen_through_workflow() {
        controller.goToHome();
        verify(workflow).goToHome();
    }

    @Test
    public void on_new_PlayersUpdateMessage_arrived_the_players_will_be_displayed() {
        PlayersUpdateMessage playersUpdateMessage = mock(PlayersUpdateMessage.class);
        String[] players = {"player"};
        when(playersUpdateMessage.getPlayersConnected()).thenReturn(players);
        controller.onNewMessageArrived(channel, playersUpdateMessage);
        verify(view).displayPlayers(players);
    }

    @Test
    public void on_new_ServerDisconnectedMessage_arrived_serverDisconnected_will_displayed_and_channel_will_be_stopped() {
        ServerDisconnectedMessage serverDisconnectedMessage = mock(ServerDisconnectedMessage.class);
        controller.onNewMessageArrived(channel, serverDisconnectedMessage);
        verify(view).serverDisconnected("Connection to server : localhost is lost");
        verify(channel).stop();
    }

    @Test
    public void on_new_RoundStartedMessage_arrived_along_with_role_then_respective_screen_should_be_displayed() {
        NightStartedMessage nightStartedMessage = mock(NightStartedMessage.class);
        Role role = mock(Role.class);
        when(nightStartedMessage.getRole()).thenReturn(role);
        ArrayList<Player> players = new ArrayList<Player>();
        when(nightStartedMessage.getPlayers()).thenReturn(players);
        controller.onNewMessageArrived(channel, nightStartedMessage);
//        verify(role).goToScreen(workflow, channel, "localhost", players);
    }

    @Test
    public void on_new_day_started_message_arrived_day_started_screen_should_be_displayed() {
        String killedPlayer = "chethan";
        ArrayList<Player> playersRemaining = new ArrayList<Player>();
        DayStartedMessage dayStartedMessage = new DayStartedMessage(killedPlayer, playersRemaining, new Player("player"));
        controller.onNewMessageArrived(channel, dayStartedMessage);
        verify(workflow).dayStarted(killedPlayer, playersRemaining, dayStartedMessage.getCurrentPlayer(), channel);
    }


}
