package ru.amfitel.jagost.net;

import ru.amfitel.jagost.api.ServerWebSocketInt;


/**
 * Created by estarcev on 14.12.2016.
 */
public class GameServer {
	private static GameServer instance;
	private ServerWebSocketInt serverWebSocketImpl;

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
		if (isHasServerImpl()) {
			serverWebSocketImpl.stop();
		}
	}

	public void setWebSocketImpl(ServerWebSocketInt serverWebSocketImpl) {
		this.serverWebSocketImpl = serverWebSocketImpl;
	}

	public boolean isHasServerImpl() {
		return serverWebSocketImpl != null;
	}


	public void startServer() {
		if(isHasServerImpl()){
			ServerEventsHandler handler = new ServerEventsHandler() {
				@Override
				public void onOpen(int hash) {
					System.out.println("server onOpen");
				}

				@Override
				public void onClose(int hash) {
					System.out.println("server onClose");
				}

				@Override
				public void onMessage(int hash, String message) {

				}

				@Override
				public void onError(int hash, Exception ex) {
					System.out.println("server onError");
				}
			};
			serverWebSocketImpl.startServer(handler);
		}
	}

}
