package controllers.server;

import controllers.ConnectionFactory;
import controllers.Workflow;
import org.junit.Before;
import org.junit.Test;
import view.server.WaitForPlayersView;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WaitForPlayersControllerTest {
    Workflow workflow;
    WaitForPlayersController controller;
    ConnectionFactory connectionFactory;

    @Before
    public void setup() {
        workflow = mock(Workflow.class);
        connectionFactory = mock(ConnectionFactory.class);
        controller = new WaitForPlayersController(workflow, connectionFactory);
    }

    @Test
    public void on_starting_the_server_connectionFactory_should_create_the_server_and_start_the_server() {
        controller.start();
        verify(connectionFactory).createServer(controller);
        verify(connectionFactory).startServer();
    }

    @Test
    public void startGame_should_show_the_StartGame_screen_through_workflow() {
        controller.startGame();
        verify(connectionFactory).getServer();
        verify(workflow).startGame(connectionFactory.getServer());
    }

    @Test
    public void stopServer_should_stop_server_through_connectionFactory_and_show_the_home_screen() {
        controller.stopServer();
        verify(connectionFactory).stopServer();
        verify(workflow).goToHome();
    }

    @Test
    public void when_player_joined_the_list_of_players_will_be_updated() {
        Player player = mock(Player.class);
        WaitForPlayersView view = mock(WaitForPlayersView.class);
        controller.bind(view);
        controller.playerJoined(player);
        verify(view).updatePlayers(new ArrayList<Player>());
    }

    @Test
    public void when_player_disconnected_the_list_of_players_will_be_updated() {
        Player player = mock(Player.class);
        WaitForPlayersView view = mock(WaitForPlayersView.class);
        controller.bind(view);
        controller.playerDisconnected(player);
        verify(view).updatePlayers(new ArrayList<Player>());
    }
}
