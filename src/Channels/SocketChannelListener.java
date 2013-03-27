package channels;

import channels.messages.ChannelMessage;

import java.io.IOException;


public interface SocketChannelListener {

    void onClose(SocketChannel channel, Exception e);

    void onSendFailed(SocketChannel channel, IOException e, ChannelMessage message);

    void onNewMessageArrived(SocketChannel channel, ChannelMessage message);

    void onMessageReadError(SocketChannel channel, Exception e);
}
