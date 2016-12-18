package ru.amfitel.jagost.desktop.impl;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import ru.amfitel.jagost.api.ClientWebSocketInt;
import ru.amfitel.jagost.net.ClientEventHandler;

import java.net.URI;

/**
 * Created by estarcev on 16.12.2016.
 */
public class ClientWebSocketImpl implements ClientWebSocketInt {
	private WebSocketClient wsc;

	@Override
	public void joinToServer(String uri, final ClientEventHandler handler){
		close();
		URI serverURI = URI.create(uri);
		wsc = new WebSocketClient(serverURI) {
			@Override
			public void onOpen(ServerHandshake handshakedata) {
				handler.onOpen();
			}

			@Override
			public void onMessage(String message) {
				handler.onMessage(message);
			}

			@Override
			public void onClose(int code, String reason, boolean remote) {
				handler.onClose();
			}

			@Override
			public void onError(Exception ex) {
				handler.onError(ex);
			}
		};
		wsc.connect();
	}

	@Override
	public void close() {
		if(wsc != null){
			wsc.close();
		}
	}

	@Override
	public void sendMessage(String msg) {
		if(wsc != null) {
			wsc.send(msg);
		}
	}
}
