package channels;

import channels.messages.ChannelMessage;

import java.io.IOException;

public interface SocketChannelListener{
    void onConnectionEstablished(SocketChannel channel);
    void onConnectionFailed(String serverAddress, int serverPort, Exception e);
    void onClose(SocketChannel channel, Exception e);
    void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message);
    void onNewMessageArrived(SocketChannel channel, ChannelMessage message);
    void onMessageReadError(SocketChannel channel, Exception e);
}
