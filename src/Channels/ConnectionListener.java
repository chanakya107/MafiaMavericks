package channels;

public interface ConnectionListener {
    void onConnectionEstablished(SocketChannel channel);
    void onConnectionFailed(String serverAddress, int serverPort, Exception e);

}
