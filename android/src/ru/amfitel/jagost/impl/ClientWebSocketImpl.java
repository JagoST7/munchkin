package ru.amfitel.jagost.impl;

import ru.amfitel.jagost.api.ClientWebSocketInt;
import ru.amfitel.jagost.net.ClientEventHandler;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import ru.amfitel.jagost.net.MessageInt;


import java.net.URI;


/**
 * Created by st_ni on 18.12.2016.
 */
public class ClientWebSocketImpl implements ClientWebSocketInt {
    private WebSocketClient wsc;

    @Override
    public void joinToServer(String uri, final ClientEventHandler handler){
        close();
        URI serverURI = URI.create(uri);
        wsc = new WebSocketClient(serverURI) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                handler.onOpen();
            }

            @Override
            public void onMessage(String message) {
                MessageInt impl = new MessageImpl().parse(message);
                handler.onMessage(impl);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                handler.onClose();
            }

            @Override
            public void onError(Exception ex) {
                handler.onError(ex);
            }
        };
        wsc.connect();
    }

    @Override
    public void close() {
        if(wsc != null){
            wsc.close();
        }
    }

    @Override
    public void sendMessage(MessageInt msg) {
        if(wsc != null) {
            wsc.send(msg.toString());
        }
    }

}
