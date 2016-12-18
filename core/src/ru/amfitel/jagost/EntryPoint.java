package ru.amfitel.jagost;

import com.badlogic.gdx.Game;
import ru.amfitel.jagost.api.ClientWebSocketInt;
import ru.amfitel.jagost.api.ServerWebSocketInt;
import ru.amfitel.jagost.menu.MainMenuScreen;
import ru.amfitel.jagost.net.GameClient;
import ru.amfitel.jagost.net.GameServer;

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

	public void setWebSocketImpl(ServerWebSocketInt serverWebSocketInt){
		GameServer.getInstance().setWebSocketImpl(serverWebSocketInt);
	}

	public void setWebSocketImpl(ClientWebSocketInt clientWebSocketInt){
		GameClient.getInstance().setWebSocketImpl(clientWebSocketInt);
	}
}
