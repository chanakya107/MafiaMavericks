package controllers;

import channels.SocketChannel;
import channels.SocketChannelListener;

public class ConnectionFactory {
    public void connectToServer(String serverName, int serverPort, SocketChannelListener listener) {
        SocketChannel.connectTo(serverName, serverPort, listener);
    }
}
