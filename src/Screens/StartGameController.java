package screens;

import channels.server.SocketServer;
import channels.server.SocketServerListener;
import channels.SocketChannel;
import view.StartGameView;

public class StartGameController implements SocketServerListener {
    private Workflow workflow;
    private StartGameView view;
    private SocketServer server = new SocketServer(1254,this);

    public StartGameController(Workflow workflow) {

        this.workflow = workflow;
    }

    public void start() {
        server.start();
    }

    public void bind(StartGameView view) {
        this.view = view;
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
    }

    @Override
    public void onError(Exception e) {
    }

    public void startGame() {
        workflow.startGame();
    }

    public void stopServer() {
        server.stop();
        workflow.goBackToHome();
    }
}
