package view.client;

import javax.swing.*;

public interface ClientDetailsView {
    String getServerName();

    String getPlayerName();

    void display(String message);
}
