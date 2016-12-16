package ru.amfitel.jagost.server;

/**
 * Created by estarcev on 16.12.2016.
 */
public interface ClientEventHandler {

	void onOpen();

	void onMessage(String message);

	void onClose();

	void onError(Exception ex);
}
