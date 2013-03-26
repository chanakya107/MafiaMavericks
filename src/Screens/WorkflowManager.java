package screens;

public class WorkflowManager implements Workflow {
    private MainFrame mainFrame;

    public void start() {
        mainFrame = new MainFrame();
        HomeController controller =new HomeController(this);
        controller.bind(new HomeScreen(mainFrame,controller));
        controller.start();
    }

    @Override
    public void startServer() {

    }

    @Override
    public void joinServer() {
    }
}
