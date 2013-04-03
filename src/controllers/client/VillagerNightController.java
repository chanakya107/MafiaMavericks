package controllers.client;

import channels.SocketChannel;
import channels.SocketChannelListener;
import channels.messages.ChannelMessage;
import controllers.Workflow;
import controllers.server.Player;
import messages.ServerDisconnectedMessage;
import view.client.VillagerNightView;

import java.io.IOException;
import java.util.List;

public class VillagerNightController implements SocketChannelListener {
    private final Workflow workflow;
    private final SocketChannel channel;
    private final String serverName;
   private VillagerNightView view;

    public VillagerNightController(Workflow workflow, SocketChannel channel, String serverName) {

        this.workflow = workflow;
        this.channel = channel;
        this.serverName = serverName;
    }

    public void bind(VillagerNightView view) {

        this.view = view;
    }

    public void start() {
    }

    public void disconnectingFromServer() {
        channel.stop();
        workflow.getGameDetails();
    }

    public void goToHome() {
        workflow.goToHome();
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
