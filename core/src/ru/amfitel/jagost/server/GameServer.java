package ru.amfitel.jagost.server;

import com.badlogic.gdx.net.ServerSocket;
import ru.amfitel.jagost.api.WebSocketServerInt;

/**
 * Created by estarcev on 14.12.2016.
 */
public class GameServer {
	private static GameServer instance;
	private ServerSocket serverSocket;
	private WebSocketServerInt webSocketImpl;

	private GameServer() {

	}

	public static GameServer getInstance(){
		if(instance == null) {
			instance = new GameServer();
		}
		return instance;
	}

	public boolean isStarted() {
		return webSocketImpl != null && webSocketImpl.isStarted();
	}

	public void dispose() {
		serverSocket.dispose();
		//TODO
	}

	public void setWebSocketImpl(WebSocketServerInt webSocketImpl) {
		this.webSocketImpl = webSocketImpl;
	}

	public boolean isCanStart() {
		return webSocketImpl != null;
	}
}
