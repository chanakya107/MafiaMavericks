package controllers;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HomeControllerTest {
    @Test
    public void startServer_should_display_startServerScreen(){
        Workflow workflow = mock(Workflow.class);
        HomeController controller = new HomeController(workflow);
        controller.startServer();
        verify(workflow).startServer();
    }

    @Test
    public void getPlayerDetails_should_display_startServerScreen(){
        Workflow workflow = mock(Workflow.class);
        HomeController controller = new HomeController(workflow);
        controller.getPlayerDetails();
        verify(workflow).getGameDetails();
    }


}
