package ru.amfitel.jagost.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.amfitel.jagost.server.GameServer;


/**
 * Created by estarcev on 14.12.2016.
 */
public class StartServerScreen implements Screen {

	private final Game game;
	Stage stage;
	Skin skin;
	TextArea log;

	public StartServerScreen(final Game game) {
		this.game = game;

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		skin = BasicSkin.getSkin(TextField.class);

		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();

		log = new TextArea("",skin);
		stage.addActor(log);

		GameServer.getInstance().startServer();

		log.setText("Start server on : " + GameServer.getOwnIpAddress());
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
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		setActorsPositions(width, height);
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

	private void setActorsPositions(int width, int height) {
		log.setPosition(10,40);
		log.setWidth(width-20);
		log.setHeight(height-50);
	}

	private void addLineToLog(String line) {
		log.setText(log.getText()+"/n"+line);
	}
}
