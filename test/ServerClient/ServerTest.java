package ServerClient;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ServerTest {
    @Test
    public void server_when_connected_to_a_client_gives_a_connected_message() throws IOException {
        Server server = Server.createServer(1);
        server.startServer();
        Client client = Client.createClient("localhost", 1254);
        server.listen();
        server.sendMessage();
        Assert.assertEquals("Connected", client.getMessage());
        server.close();
    }

    @Test
    public void connection_of_two_clients_with_the_server_gives_two_connected_message() throws IOException {
        Server server = Server.createServer(2);
        server.startServer();
        Client one = Client.createClient("localhost", 1254);
        Client two = Client.createClient("localhost", 1254);
        server.listen();
        server.sendMessage();
        Assert.assertEquals("Connected", one.getMessage());
        Assert.assertEquals("Connected", two.getMessage());
        server.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createServer_with_0_clients_throws_IllegalArgumentException() {
        Server.createServer(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_server_with_negative_number_of_clients_throws_IllegalArgumentException() {
        Server.createServer(-1);
    }
}