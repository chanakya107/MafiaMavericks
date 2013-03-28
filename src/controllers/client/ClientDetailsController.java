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
        if (!view.getServerName().equals("")){
        connectionFactory.connectToServer(view.getServerName(), 1254, this); }
        else{
           serverNotFound();
        }
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
//        if (!view.getServerName().equals(""))
            workflow.connectedToServer(channel, view.getServerName(), view.getPlayerName());
//        else
//            serverNotFound();
    }

    @Override
    public  void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        serverNotFound();
    }

    public void serverNotFound() {
        String connectedMessage = view.getServerName() + " : Server Not Found";
        JOptionPane.showConfirmDialog(null, connectedMessage, "", JOptionPane.DEFAULT_OPTION);
//        workflow.getGameDetails();
    }

    public void disconnect() {

        workflow.goBackToHome();
    }


    public void start() {

    }
}
