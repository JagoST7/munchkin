package ru.amfitel.jagost.desktop.impl;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import ru.amfitel.jagost.api.ServerWebSocketInt;
import org.java_websocket.server.WebSocketServer;
import ru.amfitel.jagost.net.MessageInt;
import ru.amfitel.jagost.net.ServerEventsHandler;

import java.io.IOException;
import java.net.InetSocketAddress;


/**
 * Created by estarcev on 14.12.2016.
 * https://github.com/pepedeab/libGDX-Net
 * https://github.com/TooTallNate/Java-WebSocket
 */
public class ServerWebSocketImpl implements ServerWebSocketInt {

	private WebSocketServer wss;

	public void startServer(final ServerEventsHandler handler) {
		if(wss != null) {
			return;
		}
        wss = new WebSocketServer(new InetSocketAddress(PORT)) {
			@Override
			public void onOpen(WebSocket conn, ClientHandshake handshake) {
				handler.onOpen(conn.hashCode());
			}

			@Override
			public void onClose(WebSocket conn, int code, String reason, boolean remote) {
				handler.onClose(conn.hashCode());
			}

			@Override
			public void onMessage(WebSocket conn, String message) {
				MessageInt impl = MessageImpl.parseStat(message);
				handler.onMessage(conn.hashCode(), impl);
			}

			@Override
			public void onError(WebSocket conn, Exception ex) {
				handler.onError(conn.hashCode(), ex);
			}
		};
		wss.start();
	}

	@Override
	public void stop() {
		if(wss!= null) {
			try {
				wss.stop();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void sendMessage(MessageInt msg, int... hashes) {
		if (wss != null) {
			for (WebSocket conn : wss.connections()) {
				if (hashes != null && hashes.length>0) {
					for (int hash : hashes) {
						if (hash == conn.hashCode()) {
							conn.send(msg.toString());
						}
					}
				} else {
					conn.send(msg.toString());
				}
			}
		}
	}

	@Override
	public MessageInt getNewMessage() {
		return MessageImpl.parseStat(null);
	}

}


