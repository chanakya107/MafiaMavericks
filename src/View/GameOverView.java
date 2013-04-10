package view;

import controllers.server.Role;

import java.util.List;

public interface GameOverView {
    void displayWinner(Role winner);

    void displayLog(List<String> log);
}
