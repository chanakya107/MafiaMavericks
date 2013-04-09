package controllers;

import org.junit.Before;
import org.junit.Test;
import view.HomeView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HomeControllerTest {
    Workflow workflow;
    HomeController controller;

    @Before
    public void setup() {
        workflow = mock(Workflow.class);
        controller = new HomeController(workflow);
    }

    @Test
    public void startServer_should_display_startServerScreen() {
        controller.startServer();
        verify(workflow).startServer();
    }

    @Test
    public void getPlayerDetails_should_display_startServerScreen() {
        controller.getPlayerDetails();
        verify(workflow).getGameDetails();
    }

    @Test
    public void when_server_disconnected_connection_to_server_lost_should_be_displayed() {
        HomeView view = mock(HomeView.class);
        controller.bind(view);
        controller.displayError("player");
        verify(view).displayError("player");
    }

}
