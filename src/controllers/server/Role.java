package controllers.server;

import channels.SocketChannel;
import controllers.Workflow;

import java.io.Serializable;

public enum Role implements Serializable {
    Mafia() {
        @Override
        public void goToScreen(Workflow workflow, SocketChannel channel, String serverName) {
            workflow.mafiaNightScreen(channel, serverName);
        }
    },
    Villager() {
        @Override
        public void goToScreen(Workflow workflow, SocketChannel channel, String serverName) {
            workflow.villagerNightScreen(channel, serverName);
        }
    };

    public void goToScreen(Workflow view, SocketChannel channel, String serverName) {

    }
}
