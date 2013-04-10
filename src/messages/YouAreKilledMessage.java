package messages;

import channels.messages.ChannelMessage;

public class YouAreKilledMessage extends ChannelMessage {
    private String name;

    public YouAreKilledMessage(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
