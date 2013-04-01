package controllers.client;

import channels.SocketChannel;
import channels.SocketChannelListener;
import channels.messages.ChannelMessage;
import controllers.Workflow;
import controllers.server.God;
import messages.PlayersUpdateMessage;
import messages.ServerDisconnectedMessage;
import screens.client.MafiaScreen;
import view.client.VillagerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        startTimer();
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

    public void goToNight() {
        workflow.goToNight(serverName,channel);
    }

    public void startTimer() {
        Runnable runner = new Runnable() {
            public void run() {
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        goToNight();
                    }
                });
                timer.start();
            }
        };
        EventQueue.invokeLater(runner);
    }
}
