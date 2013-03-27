package controllers.client;

import channels.SocketChannel;
import controllers.Workflow;
import org.junit.Test;

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

}
