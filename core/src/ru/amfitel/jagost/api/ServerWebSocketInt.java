package ru.amfitel.jagost.api;

import ru.amfitel.jagost.server.ServerEventsHandler;

/**
 * Created by estarcev on 14.12.2016.
 */
public interface ServerWebSocketInt {

    int PORT = 8989;

    void stop();

    void startServer(ServerEventsHandler handler);

    void sendMessage(String msg, int... hashes);
}
