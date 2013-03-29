package controllers.client;

import channels.SocketChannel;
import channels.SocketChannelListener;
import channels.messages.ChannelMessage;
import controllers.Workflow;
import messages.ServerDisconnectedMessage;
import view.client.VillagerView;

import java.io.IOException;

public class VillagerController implements SocketChannelListener {
    private final Workflow workflow;
    private final SocketChannel channel;
    private final String serverName;
    private VillagerView view;

    public VillagerController(Workflow workflow, SocketChannel channel, String serverName) {

        this.workflow = workflow;
        this.channel = channel;
        this.serverName = serverName;
    }

    public void bind(VillagerView view) {

        this.view = view;
    }

    public void start() {

    }

    public void disconnectingFromServer() {
        channel.stop();
        workflow.getGameDetails();
    }

    public void goToHome() {
        workflow.goBackToHome();
    }

    @Override
    public void onClose(SocketChannel channel, Exception e) {
    }

    @Override
    public void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message) {
    }

    @Override
    public void onNewMessageArrived(SocketChannel channel, ChannelMessage message) {
        if (message instanceof ServerDisconnectedMessage) {
            view.serverDisconnected(serverName);
            channel.stop();
        }
    }

    @Override
    public void onMessageReadError(SocketChannel channel, Exception e) {
    }
}
