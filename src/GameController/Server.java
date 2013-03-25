package GameController;

import Channels.Server.SocketServer;
import Channels.Server.SocketServerListener;
import Channels.SocketChannel;

public class Server implements SocketServerListener{
    public SocketServer startServer() {
        final SocketServer server = new SocketServer(1254, this);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;
    }

    @Override
    public void onError(Exception e) {
    }

    @Override
    public void onConnectionEstablished(SocketChannel channel) {
    }
}
