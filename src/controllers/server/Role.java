package controllers.server;

import view.client.JoinGameView;

public enum Role {
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
