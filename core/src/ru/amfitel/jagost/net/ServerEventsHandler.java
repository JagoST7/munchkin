package ru.amfitel.jagost.net;

/**
 * Created by estarcev on 16.12.2016.
 */
public interface ServerEventsHandler {

	void onOpen(int hash);

	void onClose(int hash);

	void onMessage(int hash, MessageInt message);

	void onError(int hash, Exception ex);
}
