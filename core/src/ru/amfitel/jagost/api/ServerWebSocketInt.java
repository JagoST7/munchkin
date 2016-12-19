package ru.amfitel.jagost.api;

import ru.amfitel.jagost.net.MessageInt;
import ru.amfitel.jagost.net.ServerEventsHandler;

/**
 * Created by estarcev on 14.12.2016.
 */
public interface ServerWebSocketInt {

    int PORT = 8989;

    void stop();

    void startServer(ServerEventsHandler handler);

    void sendMessage(MessageInt msg, int... hashes);
}
