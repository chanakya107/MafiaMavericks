package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import org.junit.Test;
import view.client.JoinGameView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class JoinGameControllerTest {
    @Test
    public void disconnecting_from_server(){
        SocketChannel socketChannel = mock(SocketChannel.class);
        Workflow workflow = mock(Workflow.class);
        JoinGameController controller = new JoinGameController(workflow, socketChannel,"localhost","player");
        controller.disconnectingFromServer();
        verify(socketChannel).stop();
        verify(workflow).getGameDetails();
    }

    @Test
    public void connect_to_server(){
        SocketChannel socketChannel = mock(SocketChannel.class);
        Workflow workflow = mock(Workflow.class);
        JoinGameView view = mock(JoinGameView.class);
        JoinGameController controller = new JoinGameController(workflow, socketChannel,"localhost","player");
        controller.bind(view);
//        when(view.connectToServer()).thenReturn("localhost","player");
//        verify(view).connectToServer();
    }
}
