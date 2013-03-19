import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//Understands providing connection to multiple clients.
public class Server {
    public static void main(String args[]) throws IOException {
        ServerSocket s = new ServerSocket(1254);
        Socket socket=s.accept();
        OutputStream socketOutputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(socketOutputStream);
        dataOutputStream.writeUTF("Hi there");
        dataOutputStream.close();
        socketOutputStream.close();
        socket.close();
    }
}
