package controllers.server;

import channels.ConnectionListener;
import channels.SocketChannel;
import channels.messages.ChannelMessage;
import controllers.Workflow;
import messages.ServerDisconnectedMessage;
import view.server.GameStartedView;

public class GameStartedController{
    private Workflow workflow;
    private GameStartedView view;

    public GameStartedController(Workflow workflow) {
        this.workflow = workflow;
    }

    public void bind(GameStartedView view) {
        this.view = view;
    }

    public void start() {

    }

//    public void stopServer() {
//        sendMessage(new ServerDisconnectedMessage());
//        workflow.getServer().stop();
//        workflow.goBackToHome();
//    }
//
//    private void sendMessage(ChannelMessage message) {
//        for (Player player : workflow.getPlayers()) {
//            player.sendMessage(message);
//        }
//    }

}
