package ru.amfitel.jagost.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.amfitel.jagost.EntryPoint;
import ru.amfitel.jagost.desktop.impl.WebSocketServerServerImpl;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		EntryPoint ep = new EntryPoint();
		new LwjglApplication(ep, config);
		ep.setWebSocketImpl(new WebSocketServerServerImpl());
	}
}
