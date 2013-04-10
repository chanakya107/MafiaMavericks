package messages;

import channels.messages.ChannelMessage;

import java.util.List;

public class YouAreKilledMessage extends ChannelMessage {
    private String name;
    private final List<String> log;

    public YouAreKilledMessage(String name, List<String> log) {

        this.name = name;
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public List<String> getLog() {
        return log;
    }
}
