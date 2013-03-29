package controllers.client;

import channels.ConnectionListener;
import channels.SocketChannel;
import controllers.ConnectionFactory;
import controllers.Workflow;
import view.client.ClientDetailsView;

public class ClientDetailsController implements ConnectionListener {
    private Workflow workflow;
    private ClientDetailsView view;
    private ConnectionFactory connectionFactory;

    public ClientDetailsController(Workflow workflow, ConnectionFactory connectionFactory) {
        this.workflow = workflow;
        this.connectionFactory = connectionFactory;
    }

    public void bind(ClientDetailsView view) {
        this.view = view;
    }

    public void connectToServer() {

        if (view.getServerName().equals("")) {
            view.display("Server Name Cannot Be Empty");
        } else if (view.getPlayerName().equals("")) {
            view.display("Player Name Cannot Be Empty");
        } else {
            connectionFactory.connectToServer(view.getServerName(), 1254, this);
        }
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        workflow.connectedToServer(channel, view.getServerName(), view.getPlayerName());
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        view.display("Unable to connect to " + view.getServerName() + " server");
    }

    public void disconnect() {
        workflow.goToHome();
    }

    public void start() {
    }
}
