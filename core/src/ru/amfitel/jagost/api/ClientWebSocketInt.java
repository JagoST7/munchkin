package ru.amfitel.jagost.api;

import ru.amfitel.jagost.net.ClientEventHandler;

/**
 * Created by estarcev on 16.12.2016.
 */
public interface ClientWebSocketInt {

	int PORT = 8989;

	void joinToServer(String uri, ClientEventHandler handler);

	void close();

	void sendMessage(String msg);
}
