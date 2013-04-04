package controllers.server;

import channels.server.SocketServer;
import controllers.Workflow;
import controllers.client.Client;
import org.junit.Before;
import org.junit.Test;
import view.server.GameStartedView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameStartedControllerTest {
    Workflow workflow;
    SocketServer server;
    List<Client> players = new ArrayList<Client>();
    GameStartedView view;
    GameStartedController controller;

    @Before
    public void setup() {
        workflow = mock(Workflow.class);
        server = mock(SocketServer.class);
        view = mock(GameStartedView.class);
        controller = new GameStartedController(workflow, server, players);
        controller.bind(view);
    }

    @Test
    public void on_Start_the_controller_should_display_all_the_players() {
        controller.start();
        verify(view).displayPlayers(players);
    }

    @Test
    public void on_stopServer_the_server_should_stop_and_the_homescreen_should_be_displayed() {
        controller.stopServer();
        verify(server).stop();
        verify(workflow).goToHome();
    }


}
