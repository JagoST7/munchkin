package ru.amfitel.jagost;

import com.badlogic.gdx.Game;
import ru.amfitel.jagost.api.WebSocketServerInt;
import ru.amfitel.jagost.menu.MainMenuScreen;
import ru.amfitel.jagost.server.GameServer;

public class EntryPoint extends Game {

	@Override
	public void create () {
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		GameServer.getInstance().dispose();
	}

	public void setWebSocketImpl(WebSocketServerInt webSocketServerInt){
		GameServer.getInstance().setWebSocketImpl(webSocketServerInt);
	}
}
