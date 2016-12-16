package ru.amfitel.jagost.server;

import ru.amfitel.jagost.api.ClientWebSocketInt;
import ru.amfitel.jagost.api.ServerWebSocketInt;

/**
 * Created by estarcev on 14.12.2016.
 */
public class GameServer {
	private static GameServer instance;
	private ServerWebSocketInt serverWebSocketImpl;
	private ClientWebSocketInt clientWebSocketImpl;

	//	private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

	private GameServer() {

	}

	public static GameServer getInstance(){
		if(instance == null) {
			instance = new GameServer();
		}
		return instance;
	}

	public void dispose() {
		if (isHasClientImpl()) {
			clientWebSocketImpl.close();
		}
		if (isHasServerImpl()) {
			serverWebSocketImpl.stop();
		}
	}

	public void setWebSocketImpl(ServerWebSocketInt serverWebSocketImpl) {
		this.serverWebSocketImpl = serverWebSocketImpl;
	}

	public void setWebSocketImpl(ClientWebSocketInt clientWebSocketImpl) {
		this.clientWebSocketImpl = clientWebSocketImpl;
	}

	public boolean isHasServerImpl() {
		return serverWebSocketImpl != null;
	}

	public boolean isHasClientImpl() {
		return clientWebSocketImpl != null;
	}

	public void startServer() {
		if(isHasServerImpl()){
			ServerEventsHandler handler = new ServerEventsHandler() {
				@Override
				public void onOpen(int hash) {

				}

				@Override
				public void onClose(int hash) {

				}

				@Override
				public void onMessage(int hash, String message) {

				}

				@Override
				public void onError(int hash, Exception ex) {

				}
			};
			serverWebSocketImpl.startServer(handler);
		}
	}

	public void joinToServer() {
		if(isHasClientImpl()) {
			ClientEventHandler handler = new ClientEventHandler() {
				@Override
				public void onOpen() {

				}

				@Override
				public void onMessage(String message) {

				}

				@Override
				public void onClose() {

				}

				@Override
				public void onError(Exception ex) {

				}
			};
			clientWebSocketImpl.joinToServer("wss://localhost:"+ClientWebSocketInt.PORT, handler);
		}
	}
}
