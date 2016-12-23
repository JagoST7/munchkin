package ru.amfitel.jagost.net;

import com.badlogic.gdx.Gdx;
import ru.amfitel.jagost.api.ClientWebSocketInt;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by st_ni on 18.12.2016.
 */
public class GameClient implements CommunicationInt {
    private static GameClient instance;
    private ClientWebSocketInt clientWebSocketImpl;

    private Set<ClientEventHandler> eventHandlers = Collections.synchronizedSet(new HashSet<ClientEventHandler>());

    private NetUser thisUser;

    private GameClient(){
        thisUser = new NetUser("UID"+Gdx.app.hashCode());   //TODO UID device
    }

    public NetUser getThisUser(){
        return thisUser;
    }

    public static GameClient getInstance(){
        if(instance == null) {
            instance = new GameClient();
        }
        return instance;
    }

    public void dispose() {
        if (isHasClientImpl()) {
            clientWebSocketImpl.close();
        }
    }

    public void setWebSocketImpl(ClientWebSocketInt clientWebSocketImpl) {
        this.clientWebSocketImpl = clientWebSocketImpl;
        thisUser.setInfo(clientWebSocketImpl.getNewMessage());
    }

    public boolean isHasClientImpl() {
        return clientWebSocketImpl != null;
    }

    public void addEventHandler(ClientEventHandler eventHandler){
        eventHandlers.add(eventHandler);
    }

    public void removeEventHandler(ClientEventHandler eventHandler){
        eventHandlers.remove(eventHandler);
    }

    public void joinToServer(String ipAddress) {
        if(isHasClientImpl()) {
            ClientEventHandler handler = new ClientEventHandler() {
                @Override
                public void onOpen() {
                    System.out.println("usr_on_open");
                    for(ClientEventHandler eventHandler: eventHandlers) {
                        eventHandler.onOpen();
                    }
                }

                @Override
                public void onMessage(MessageInt message) {
                    System.out.println("usr_on_msg:"+ message.toString());
                    for(ClientEventHandler eventHandler: eventHandlers) {
                        eventHandler.onMessage(message);
                    }
                    onServerMessage(message);
                }

                @Override
                public void onClose() {
                    System.out.println("usr_on_close");
                    for(ClientEventHandler eventHandler: eventHandlers) {
                        eventHandler.onClose();
                    }
                }

                @Override
                public void onError(Exception ex) {
                    System.out.println("usr_on_error:");
                    for(ClientEventHandler eventHandler: eventHandlers) {
                        eventHandler.onError(ex);
                    }
                }
            };
            clientWebSocketImpl.joinToServer("wss://"+ipAddress+":"+ClientWebSocketInt.PORT, handler);
        }
    }

    private void onServerMessage(MessageInt message) {
        String cmnd = message.getPropAsString(PR_COMMAND);
        if(VL_GET_INFO.equals(cmnd)) {
            MessageInt info = thisUser.getInfo();
            info.addProperty(PR_COMMAND, VL_USER_INFO);
            clientWebSocketImpl.sendMessage(info);
        }
    }

}
