package controllers.server;

import channels.SocketChannel;
import channels.messages.ChannelMessage;
import controllers.client.Client;
import messages.PlayerDetailsMessage;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ClientTest {
    private SocketChannel channel;
    private PlayerManager manager;
    private Client client;

    @Before
    public void setup() {
        channel = mock(SocketChannel.class);
        manager = mock(PlayerManager.class);
        client = new Client(channel, manager);
    }

    @Test
    public void on_creating_a_player_a_channel_should_be_bound_with_the_player() {
        verify(channel).bind(client);
    }

    @Test
    public void player_can_send_message_through_channel() {
        ChannelMessage message = new ChannelMessage();
        client.sendMessage(message);
        verify(channel).send(message);
    }

    @Test
    public void on_closing_of_the_connection_the_player_should_be_disconnected() {
        client.onClose(channel, new Exception());
        verify(manager).playerDisconnected(client);
    }

    @Test
    public void on_new_PlayerDetailMessage_arrives_the_player_should_be_joined() {
        PlayerDetailsMessage message = mock(PlayerDetailsMessage.class);
        when(message.getPlayer()).thenReturn(new Player("Player"));
        client.onNewMessageArrived(channel, message);
        verify(manager).playerJoined();
        verify(message).getPlayer();
    }
}