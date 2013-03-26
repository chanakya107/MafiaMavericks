package screens;

import channels.messages.ChannelMessage;
import channels.SocketChannel;
import channels.SocketChannelListener;
import view.ClientDetailsView;

import javax.swing.*;
import java.io.IOException;

public class ClientDetailsController implements SocketChannelListener {
    private Workflow workflow;
    private ClientDetailsView view;

    public ClientDetailsController(Workflow workflow) {

        this.workflow = workflow;
    }

    public void bind(ClientDetailsView view) {

        this.view = view;
    }

    public void connectToServer() {
        SocketChannel.connectTo(view.getServerName(), 1254, this);
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        workflow.connectedToServer(channel,view.getServerName(),view.getPlayerName());
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
        String connectedMessage =view.getServerName() + " : Server Not Found";
        JOptionPane.showConfirmDialog(null, connectedMessage, "", JOptionPane.DEFAULT_OPTION);
        workflow.getClientDetails();
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {

    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
    }

    public void disconnect() {
        workflow.goBackToHome();
    }
}
