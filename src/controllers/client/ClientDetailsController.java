package controllers.client;

import channels.ConnectionListener;
import channels.SocketChannel;
import controllers.ConnectionFactory;
import controllers.Workflow;
import view.client.ClientDetailsView;

import javax.swing.*;

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
        String serverName = view.getServerName();
        if (!serverName.equals(""))
            connectionFactory.connectToServer(serverName, 1254, this);
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        workflow.connectedToServer(channel, view.getServerName(), view.getPlayerName());
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        serverNotFound();
    }

    public void serverNotFound() {
        String connectedMessage = view.getServerName() + " : Server Not Found";
        JOptionPane.showConfirmDialog(null, connectedMessage, "", JOptionPane.DEFAULT_OPTION);
    }

    public void disconnect() {

        workflow.goBackToHome();
    }


    public void start() {

    }
}
