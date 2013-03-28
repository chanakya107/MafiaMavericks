package controllers.client;

import controllers.ConnectionFactory;
import controllers.Workflow;
import org.junit.Before;
import org.junit.Test;
import view.client.ClientDetailsView;

import static org.mockito.Mockito.*;

public class ClientDetailsControllerTest {
    private Workflow workflow;
    private ClientDetailsView view;
    private ConnectionFactory connectionFactory;
    private ClientDetailsController controller;

    @Before
    public void setup() {
        workflow = mock(Workflow.class);
        view = mock(ClientDetailsView.class);
        connectionFactory = mock(ConnectionFactory.class);
        controller = new ClientDetailsController(workflow, connectionFactory);
    }

    @Test
    public void connect_to_server_on_localhost_is_successful() {
        controller.bind(view);
        when(view.getServerName()).thenReturn("localhost");
        when(view.getPlayerName()).thenReturn("Player");
        controller.connectToServer();
        verify(connectionFactory).connectToServer("localhost", 1254, controller);
    }

    @Test
    public void connect_to_Server_on_wrong_server_name_is_not_possible() {
        controller.bind(view);
        when(view.getServerName()).thenReturn("abcd");
        when(view.getPlayerName()).thenReturn("Player");
        controller.connectToServer();
        verify(connectionFactory).connectToServer("abcd", 1254, controller);
    }

}
