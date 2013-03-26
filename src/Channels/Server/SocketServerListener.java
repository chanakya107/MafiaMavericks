package channels.Server;

import channels.SocketChannel;

public interface SocketServerListener{
    void onError(Exception e);
    void onConnectionEstablished(SocketChannel channel);
}
