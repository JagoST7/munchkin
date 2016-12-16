package ru.amfitel.jagost.server;

/**
 * Created by estarcev on 16.12.2016.
 */
public interface ServerEventsHandler {

	void onOpen(int hash);

	void onClose(int hash);

	void onMessage(int hash, String message);

	void onError(int hash, Exception ex);
}
