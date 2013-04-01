package controllers;

import channels.ConnectionListener;
import channels.SocketChannel;
import channels.server.SocketServer;
import controllers.server.WaitForPlayersController;

public class ConnectionFactory {
    private SocketServer server;

    public void connectToServer(String serverName, int serverPort, ConnectionListener listener) {
        SocketChannel.connectTo(serverName, serverPort, listener);
    }


    public void createServer(WaitForPlayersController waitForPlayersController) {
        server = new SocketServer(1254, waitForPlayersController);
    }

    public void startServer() {
        server.start();
    }

    public SocketServer getServer() {
        return server;
    }

    public void stopServer() {
        server.stop();
    }
}
