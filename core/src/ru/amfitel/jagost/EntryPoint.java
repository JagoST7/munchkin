package ru.amfitel.jagost;

import com.badlogic.gdx.Game;

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
	}
}
