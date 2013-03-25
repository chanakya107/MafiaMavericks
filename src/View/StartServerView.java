package View;

import Channels.Server.SocketServer;

public interface StartServerView {
    void onCancel(SocketServer server);

    void onStartGame();
}
