package controllers.server;

import controllers.ConnectionFactory;
import controllers.Workflow;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WaitForPlayersControllerTest {
    Workflow workflow;
    WaitForPlayersController controller;
    ConnectionFactory connectionFactory;

    @Before
    public void setup(){
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
    public void on_startGame_should_show_the_StartGame_screen_through_workflow() {
        controller.startGame();
        verify(connectionFactory).getServer();
        verify(workflow).startGame(connectionFactory.getServer(),new ArrayList<Player>());
    }

    @Test
    public void (){
        
    }
        
}
