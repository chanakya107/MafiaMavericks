package controllers.client;

import channels.SocketChannel;
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
        controller.bind(view);
    }

    @Test
    public void connect_to_server_on_localhost_is_successful() {
        when(view.getServerName()).thenReturn("localhost");
        when(view.getPlayerName()).thenReturn("Player");
        controller.connectToServer();
        verify(connectionFactory).connectToServer("localhost", 1254, controller);
    }

    @Test
    public void connectToServer_displays_serverName_cannot_be_empty_if_the_serverName_is_empty() {
        when(view.getServerName()).thenReturn("");
        when(view.getPlayerName()).thenReturn("player");
        controller.connectToServer();
        verify(view).display("Server Name Cannot Be Empty");
    }

    @Test
    public void connectToServer_displays_playerName_cannot_be_empty_if_the_playerName_is_empty() {
        when(view.getServerName()).thenReturn("localhost");
        when(view.getPlayerName()).thenReturn("");
        controller.connectToServer();
        verify(view).display("Player Name Cannot Be Empty");
    }

    @Test
    public void onConnectionEstablished_displays_connectedToServer_screen_with_serverName_and_playerName(){
        when(view.getServerName()).thenReturn("localhost");
        when(view.getPlayerName()).thenReturn("player");
        SocketChannel socketChannel = mock(SocketChannel.class);
        controller.onConnectionEstablished(socketChannel);
        verify(workflow).connectedToServer(socketChannel,"localhost","player");
    }

    @Test
    public void onConnectionFailed_displays_unableToConnect_on_the_screen_with_serverName_and_playerName(){
        when(view.getServerName()).thenReturn("abcd");
        when(view.getPlayerName()).thenReturn("player");
        controller.onConnectionFailed("localhost", 1254, new Exception());
        verify(view).getServerName();
        verify(view).display("Unable to connect to " + "abcd" + " server");
    }

    @Test
    public void on_disconnecting_the_server_it_should_go_back_to_home_screen(){
        controller.disconnect();
        verify(workflow).goBackToHome();
    }
}
