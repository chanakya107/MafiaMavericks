package controllers.server;

import channels.SocketChannel;
import controllers.Workflow;

import java.io.Serializable;
import java.util.List;

public enum Role implements Serializable {
    Mafia() {
        @Override
        public void goToScreen(Workflow workflow, SocketChannel channel, String serverName, List<Player> players, Player currentPlayer) {
            workflow.mafiaNightScreen(channel, serverName, players, currentPlayer);
        }
    },
    Villager() {
        @Override
        public void goToScreen(Workflow workflow, SocketChannel channel, String serverName, List<Player> players, Player currentPlayer) {
            workflow.villagerNightScreen(channel, serverName, players, currentPlayer);
        }
    }, Killed;

    public void goToScreen(Workflow view, SocketChannel channel, String serverName, List<Player> players, Player currentPlayer) {

    }
}
