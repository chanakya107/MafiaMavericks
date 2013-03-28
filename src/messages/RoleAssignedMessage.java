package messages;

import channels.messages.ChannelMessage;
import controllers.server.Role;

public class RoleAssignedMessage extends ChannelMessage {
    private Role role;

    public RoleAssignedMessage(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
