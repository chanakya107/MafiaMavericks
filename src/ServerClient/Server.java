package ServerClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//Understands providing connection to multiple clients.
public class Server {
    private static final int PORT = 1254;
    private ServerSocket serverSocket;
    private int backlog;
    private List<Socket> sockets = new ArrayList<Socket>();

    public Server(int backlog) {
        this.backlog = backlog;
    }

    public void stop() throws IOException {
        for (Socket socket : sockets) {
            socket.close();
        }
        serverSocket.close();
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void listen() throws IOException {
        for (int i = 0; i < backlog; i++) {
            Socket socket = serverSocket.accept();
            sockets.add(socket);
        }
    }

    public void sendMessage() throws IOException {
        for (Socket socket : sockets) {
            try {
                OutputStream socketOutputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(socketOutputStream);
                String message = "Connected";
                dataOutputStream.writeUTF(message);
                dataOutputStream.close();
                socketOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}