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
        StartGameController controller = new StartGameController(this);
        controller.bind(new StartGameScreen(mainFrame,controller));
        controller.start();
    }

    @Override
    public void joinServer() {
    }

    @Override
    public void startGame() {
        WelcomeController controller = new WelcomeController(this);
        controller.bind(new WelcomeScreen(mainFrame,controller));
        controller.start();
    }

    @Override
    public void goBackToHome() {
        HomeController controller = new HomeController(this);
        controller.bind(new HomeScreen(mainFrame,controller));
        controller.start();
    }
}
