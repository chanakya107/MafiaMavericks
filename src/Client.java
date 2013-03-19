import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
//Understands to connect to a server.
public class Client {
    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("10.4.31.92",1254);
        InputStream socketInputStream = socket.getInputStream();
        DataInputStream dis = new DataInputStream(socketInputStream);
        String message = dis.readUTF();
        System.out.println(message);
        dis.close();
        socketInputStream.close();
        socket.close();
    }
}
