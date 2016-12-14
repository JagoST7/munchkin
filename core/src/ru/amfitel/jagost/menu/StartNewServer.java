package ru.amfitel.jagost.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 * Created by estarcev on 14.12.2016.
 */
public class StartNewServer implements Screen {

	private final Game game;
	Stage stage;
	Skin skin;

	public StartNewServer(final Game game) {
		this.game = game;
		Gdx.graphics.setContinuousRendering(false);

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);// Make the stage consume events
		skin = BasicSkin.getSkin();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();

		if(Gdx.input.isTouched()) {
			//TODO
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		skin.dispose();
		stage.dispose();
	}


}
