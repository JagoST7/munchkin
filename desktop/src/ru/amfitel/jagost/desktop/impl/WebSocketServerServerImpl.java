package ru.amfitel.jagost.desktop.impl;

import ru.amfitel.jagost.api.WebSocketServerInt;
import org.java_websocket.server.WebSocketServer;

/**
 * Created by estarcev on 14.12.2016.
 * https://github.com/pepedeab/libGDX-Net
 * https://github.com/TooTallNate/Java-WebSocket
 */
public class WebSocketServerServerImpl implements WebSocketServerInt {

	private WebSocketServer wss;

	public WebSocketServerServerImpl(){

	}

	public void startServer(int port) {

	}
}

/*
* project(":desktop") {
    apply plugin: "java"


    dependencies {
        compile project(":core")
        compile "com.badlogicgames.gdx:gdx-backend-lwjgl:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop"
        compile "org.java-websocket:Java-WebSocket:1.3.0"
    }
}
* */
