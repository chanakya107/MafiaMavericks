package ServerClient;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

//Understands to connect to a server.
public class Client {
    private Socket socket;

    private Client(Socket socket) {
        this.socket = socket;
    }

    public static Client createClient(String serverName, int portNumber) throws IOException {
        if (serverName == null || serverName.matches(" +")) throw new IllegalArgumentException("server name cannot be null");
        return new Client(new Socket(serverName, portNumber));
    }

    public String getMessage() {
        String message = null;
        try {
            InputStream socketInputStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(socketInputStream);
            message = dis.readUTF();
            dis.close();
            socketInputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

}
