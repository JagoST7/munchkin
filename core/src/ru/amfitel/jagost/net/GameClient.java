package ru.amfitel.jagost.net;

import ru.amfitel.jagost.api.ClientWebSocketInt;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by st_ni on 18.12.2016.
 */
public class GameClient {
    private static GameClient instance;
    private ClientWebSocketInt clientWebSocketImpl;

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

}
