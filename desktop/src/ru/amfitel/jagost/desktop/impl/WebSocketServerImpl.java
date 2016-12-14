package ru.amfitel.jagost.desktop.impl;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import ru.amfitel.jagost.api.WebSocketServerInt;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;


/**
 * Created by estarcev on 14.12.2016.
 * https://github.com/pepedeab/libGDX-Net
 * https://github.com/TooTallNate/Java-WebSocket
 */
public class WebSocketServerImpl implements WebSocketServerInt {

	private WebSocketServer wss;

//	private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

	public WebSocketServerImpl(){

	}

	public boolean isStarted(){
		return wss != null;
	}

	public void startServer(int port) {
		if(wss!= null) {
			try {
				wss.stop();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        wss = new WebSocketServer(new InetSocketAddress(port)) {
			@Override
			public void onOpen(WebSocket conn, ClientHandshake handshake) {
				System.out.println("open :"+conn.getRemoteSocketAddress().getHostString());

			}

			@Override
			public void onClose(WebSocket conn, int code, String reason, boolean remote) {

			}

			@Override
			public void onMessage(WebSocket conn, String message) {

			}

			@Override
			public void onError(WebSocket conn, Exception ex) {

			}
		};
	}
}


