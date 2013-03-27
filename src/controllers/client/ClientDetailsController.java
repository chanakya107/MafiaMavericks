package controllers.client;

import channels.ConnectionListener;
import channels.SocketChannel;
import controllers.Workflow;
import view.ClientDetailsView;

import javax.swing.*;

public class ClientDetailsController implements ConnectionListener {
    private Workflow workflow;
    private ClientDetailsView view;
    private controllers.connectionFactory connectionFactory;

    public ClientDetailsController(Workflow workflow, controllers.connectionFactory connectionFactory) {

        this.workflow = workflow;

        this.connectionFactory = connectionFactory;
    }

    public void bind(ClientDetailsView view) {

        this.view = view;
    }

    public void connectToServer() {
        connectionFactory.connectToServer(view.getServerName(), 1254, this);
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        if (!view.getServerName().equals(""))
            workflow.connectedToServer(channel, view.getServerName(), view.getPlayerName());
        else
            serverNotFound();
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        serverNotFound();
    }

    private void serverNotFound() {
        String connectedMessage = view.getServerName() + " : Server Not Found";
        JOptionPane.showConfirmDialog(null, connectedMessage, "", JOptionPane.DEFAULT_OPTION);
        workflow.getClientDetails();
    }

    public void disconnect() {

        workflow.goBackToHome();
    }
}
