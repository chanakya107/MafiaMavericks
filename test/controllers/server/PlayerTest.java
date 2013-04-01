package controllers.server;

import channels.SocketChannel;
import channels.messages.ChannelMessage;
import messages.PlayerDetailsMessage;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayerTest {
    private SocketChannel channel;
    private PlayerManager manager;
    private Player player;

    @Before
    public void setup() {
        channel = mock(SocketChannel.class);
        manager = mock(PlayerManager.class);
        player = new Player(channel, manager);
    }

    @Test
    public void on_creating_a_player_a_channel_should_be_bound_with_the_player() {
        verify(channel).bind(player);
    }

    @Test
    public void player_can_send_message_through_channel() {
        ChannelMessage message = mock(ChannelMessage.class);
        player.sendMessage(message);
        verify(channel).send(message);
    }

    @Test
    public void on_closing_of_the_connection_the_player_should_be_disconnected() {
        player.onClose(channel, new Exception());
        verify(manager).playerDisconnected(player);
    }

    @Test
    public void on_new_PlayerDetailMessage_arrives_the_player_should_be_joined() {
        PlayerDetailsMessage message = mock(PlayerDetailsMessage.class);
        player.onNewMessageArrived(channel, message);
        verify(manager).playersJoined(player);
    }

}
