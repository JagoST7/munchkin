package ru.amfitel.jagost.server;

import ru.amfitel.jagost.api.ClientWebSocketInt;
import ru.amfitel.jagost.api.ServerWebSocketInt;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

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

	public void joinToServer(String ipAddress, final ClientEventHandler connectHandler) {
		if(isHasClientImpl()) {
			ClientEventHandler handler = new ClientEventHandler() {
				@Override
				public void onOpen() {
					connectHandler.onOpen();
					System.out.println("client onOpen");
				}

				@Override
				public void onMessage(String message) {

				}

				@Override
				public void onClose() {

				}

				@Override
				public void onError(Exception ex) {
					connectHandler.onError(ex);
					System.out.println("client onError");
				}
			};
			clientWebSocketImpl.joinToServer("wss://"+ipAddress+":"+ClientWebSocketInt.PORT, handler);
		}
	}

	public static String getOwnIpAddress() {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface ni : Collections.list(interfaces)) {
				for (InetAddress address : Collections.list(ni.getInetAddresses())) {
					if (!address.isLoopbackAddress() && address instanceof Inet4Address) {
						return (address.getHostAddress());
					}
				}
			}
		} catch (Exception e) {
			//do nothing
		}
		return "Error in get own ip";
	}
}
