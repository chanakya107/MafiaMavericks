package gameController;

import channels.Messages.ChannelMessage;
import channels.SocketChannel;
import channels.SocketChannelListener;

import java.io.IOException;
import java.net.Socket;

public class Client implements SocketChannelListener {
    SocketChannel socketChannel;

    public boolean createClient(String serverName) {
        try {
            if (!serverName.equals("")) {
                socketChannel = new SocketChannel(new Socket(serverName, 1254));
                socketChannel.bind(this);
                return true;
            } else
                return false;
        } catch (IOException e) {
            onConnectionFailed(serverName, 1254, e);
            return false;
        }
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
        this.socketChannel = channel;
        socketChannel.bind(this);
    }

    @Override
    public void onConnectionFailed(String serverAddress, int serverPort, Exception e) {
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
}
