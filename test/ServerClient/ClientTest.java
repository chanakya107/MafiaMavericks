package ServerClient;

import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;

public class ClientTest {
    private static final int PORTNUMBER = 1254;
    @Test
    public void client_when_connected_with_server_will_get_connected_message() throws IOException {
        Server server = Server.createServer(1);
        server.start();
        Client client = Client.createClient("localhost", PORTNUMBER);
        server.listen();
        server.sendMessage();
        Assert.assertEquals("Connected", client.getMessage());
        server.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClient_with_serverName_as_null_throws_IllegalArgumentException() throws IOException {
        Server server = Server.createServer(1);
        server.start();
        try {
            Client.createClient(null, PORTNUMBER);
        } finally {
            server.close();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClient_with_serverName_as_spaces_throws_IllegalArgumentException() throws IOException {
        Server server = Server.createServer(1);
        server.start();
        try {
            Client.createClient("   ", PORTNUMBER);
        } finally {
            server.close();
        }
    }
}
