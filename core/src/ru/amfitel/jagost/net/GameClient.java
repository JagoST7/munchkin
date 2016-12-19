package ru.amfitel.jagost.net;

import ru.amfitel.jagost.api.ClientWebSocketInt;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by st_ni on 18.12.2016.
 */
public class GameClient {
    private static GameClient instance;
    private ClientWebSocketInt clientWebSocketImpl;

    private Set<ClientEventHandler> eventHandlers = Collections.synchronizedSet(new HashSet<ClientEventHandler>());

    private GameClient(){}

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
                    for(ClientEventHandler eventHandler: eventHandlers) {
                        eventHandler.onOpen();
                    }
                }

                @Override
                public void onMessage(MessageInt message) {
                    for(ClientEventHandler eventHandler: eventHandlers) {
                        eventHandler.onMessage(message);
                    }
                }

                @Override
                public void onClose() {
                    for(ClientEventHandler eventHandler: eventHandlers) {
                        eventHandler.onClose();
                    }
                }

                @Override
                public void onError(Exception ex) {
                    for(ClientEventHandler eventHandler: eventHandlers) {
                        eventHandler.onError(ex);
                    }
                }
            };
            clientWebSocketImpl.joinToServer("wss://"+ipAddress+":"+ClientWebSocketInt.PORT, handler);
        }
    }

}
