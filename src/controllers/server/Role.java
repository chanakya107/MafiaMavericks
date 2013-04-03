package controllers.server;

import view.client.JoinGameView;

import java.io.Serializable;

public enum Role  implements Serializable {
    Mafia() {
        @Override
        public void goToScreen(JoinGameView view) {
            view.goToMafiaScreen();
        }
    },
    Villager() {
        @Override
        public void goToScreen(JoinGameView view) {
            view.goToVillagerScreen();
        }
    };

    public void goToScreen(JoinGameView view) {

    }
}
