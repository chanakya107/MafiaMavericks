package channels;

import channels.messages.ByeMessage;
import channels.messages.ChannelMessage;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketChannel {
    final private int SOCKET_TIMEOUT = 1000;
    Socket socket;
    private SocketChannelListener channelListener;
    private Thread thread;
    private boolean stopWaiting;


    public SocketChannel(Socket socket) {
        this.socket = socket;
    }

    public static void connectTo(final String serverAddress, final int serverPort, final ConnectionListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectSocket(serverAddress, serverPort, listener);
            }
        }).start();
    }

    private static void connectSocket(String serverAddress, int serverPort, ConnectionListener listener) {
        try {
            Socket clientSocket = new Socket(serverAddress, serverPort);
            listener.onConnectionEstablished(new SocketChannel(clientSocket));
        } catch (IOException e) {
            listener.onConnectionFailed(serverAddress, serverPort, e);
        }
    }

    public void bind(SocketChannelListener listener) {
        this.channelListener = listener;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                pollForIncomingData();
            }
        });
        thread.start();
    }

    private void pollForIncomingData() {
        try {
            socket.setSoTimeout(SOCKET_TIMEOUT);
            while (waitForData()) ;
            if (!socket.isClosed()) socket.close();
            channelListener.onClose(this, null);
        } catch (Exception e) {
            channelListener.onClose(this, e);
        }
    }

    public void send(final ChannelMessage message) {
        if (socket.isClosed()) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendData(message);
            }
        }).start();
    }

    private void sendData(ChannelMessage message) {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
            stream.writeObject(message);
        } catch (IOException e) {
            channelListener.onSendFailed(this, e, message);
        }
    }

    public String getAddress() {
        return socket.getRemoteSocketAddress().toString();
    }

    public void stop() {
        send(new ByeMessage());
        stopWaiting = true;
    }

    private boolean waitForData() {
        try {

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object o = inputStream.readObject();

            ChannelMessage message = (ChannelMessage) o;
            if (message instanceof ByeMessage) return false;
            channelListener.onNewMessageArrived(this, message);

        } catch (SocketTimeoutException e) {
            if (stopWaiting) {
                return false;
            }
        } catch (EOFException e) {
            return false;
        } catch (IOException e) {
            channelListener.onMessageReadError(SocketChannel.this, e);
        } catch (ClassNotFoundException e) {
            channelListener.onMessageReadError(SocketChannel.this, e);
        }
        return true;
    }
}
