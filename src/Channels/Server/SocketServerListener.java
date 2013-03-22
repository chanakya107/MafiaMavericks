package Channels.Server;

import Channels.SocketChannel;

public interface SocketServerListener{
    void onError(Exception e);
    void onConnectionEstablished(SocketChannel channel);
}
