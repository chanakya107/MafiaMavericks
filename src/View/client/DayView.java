package view.client;

import java.util.List;

public interface DayView {
    void display();

    String getSelectedPlayer();

    void disableConfirm();

    void displayLog(List<String> log);
}
