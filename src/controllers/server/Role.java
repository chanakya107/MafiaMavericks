package controllers.server;

import channels.SocketChannel;
import controllers.Workflow;

import java.io.Serializable;
import java.util.List;

public enum Role implements Serializable {
    Mafia() {
        @Override
        public void goToScreen(Workflow workflow, SocketChannel channel, String serverName, List<Player> players, Player currentPlayer, List<String> log) {
            workflow.mafiaNightScreen(channel, serverName, players, currentPlayer,log);
        }
    },
    Villager() {
        @Override
        public void goToScreen(Workflow workflow, SocketChannel channel, String serverName, List<Player> players, Player currentPlayer, List<String> log) {
            workflow.villagerNightScreen(channel, serverName, players, currentPlayer,log);
        }
    }, Killed;

    public void goToScreen(Workflow view, SocketChannel channel, String serverName, List<Player> players, Player currentPlayer, List<String> log) {

    }
}
