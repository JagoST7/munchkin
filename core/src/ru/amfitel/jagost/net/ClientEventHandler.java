package ru.amfitel.jagost.net;

/**
 * Created by estarcev on 16.12.2016.
 */
public interface ClientEventHandler {

	void onOpen();

	void onMessage(MessageInt message);

	void onClose();

	void onError(Exception ex);
}
