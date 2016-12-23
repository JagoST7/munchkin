package ru.amfitel.jagost.net;

import ru.amfitel.jagost.api.ServerWebSocketInt;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by estarcev on 14.12.2016.
 */
public class GameServer implements CommunicationInt {
	private static GameServer instance;
	private ServerWebSocketInt serverWebSocketImpl;

	private static Set<NetUser> netUsers = Collections.synchronizedSet(new HashSet<NetUser>());

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
					System.out.println("srv_usr_connect:"+hash+":");
					MessageInt msg = serverWebSocketImpl.getNewMessage();
					msg.addProperty(PR_COMMAND, VL_GET_INFO);
					serverWebSocketImpl.sendMessage(msg, hash);
				}

				@Override
				public void onClose(int hash) {
					System.out.println("srv_usr_close:"+hash+":");
					NetUser nu = getUserByHash(hash);
					if(nu != null) {
						nu.setStatus(NetUser.Status.DISCONNECTED);
						nu.setHash(-1);
					}
				}

				@Override
				public void onMessage(int hash, MessageInt message) {
					System.out.println("srv_in_msg:"+hash+":" + message.toString());
					onPeersMessage(hash, message);
				}

				@Override
				public void onError(int hash, Exception ex) {
					System.out.println("srv_usr_error:"+hash+":" + ex.getMessage());
				}
			};
			serverWebSocketImpl.startServer(handler);
		}
	}

	private void onPeersMessage(int hash, MessageInt message) {
		String cmnd = message.getPropAsString(PR_COMMAND);
		if(VL_USER_INFO.equals(cmnd)) {
			String uid = message.getPropAsString(PR_USER_UID);
			if(uid != null) {
				NetUser nu = getUserByUID(uid);
				if(nu == null) {
					nu = new NetUser(uid);
					netUsers.add(nu);
				}
				nu.setInfo(message);
				nu.setStatus(NetUser.Status.CONNECTED);
				nu.setHash(hash);
			}
		}
	}

	private NetUser getUserByUID(String uid) {
		for(NetUser user : netUsers) {
			if(user.getUID().equals(uid)){
				return user;
			}
		}
		return null;
	}

	private NetUser getUserByHash(int hash) {
		for(NetUser user : netUsers) {
			if(user.getHash() == hash){
				return user;
			}
		}
		return null;
	}
}
