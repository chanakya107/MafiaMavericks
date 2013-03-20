package ServerClient;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ServerTest {
    @Test
    public void server_when_connected_to_a_client_gives_a_connected_message() throws IOException {
        Server server = new Server(1);
        server.start();
        Client client = Client.createClient();
        server.listen();
        server.sendMessage();
        Assert.assertEquals("Connected",client.getMessage());
        client.close();
        server.stop();
    }
}
