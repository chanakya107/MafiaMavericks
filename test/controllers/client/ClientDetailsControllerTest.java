package controllers.client;

import controllers.Workflow;
import org.junit.Test;
import view.ClientDetailsView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientDetailsControllerTest {
    @Test
    public void connect_to_server(){
        Workflow workflow = mock(Workflow.class);
        ClientDetailsView view = mock(ClientDetailsView.class);
        ClientDetailsController controller = new ClientDetailsController(workflow);
        controller.bind(view);
        when(view.getServerName()).thenReturn("localhost");
        controller.connectToServer();
    }
}
