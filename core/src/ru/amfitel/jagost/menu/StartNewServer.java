package ru.amfitel.jagost.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/**
 * Created by estarcev on 14.12.2016.
 */
public class StartNewServer implements Screen {

	private final Game game;
	Stage stage;
	Skin skin;

	TextField portInput;

	public StartNewServer(final Game game) {
		this.game = game;

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);// Make the stage consume events
		skin = BasicSkin.getSkin(TextField.class, TextButton.class);

		portInput = new TextField("8989",skin);
		portInput.setDisabled(false);
		stage.addActor(portInput);
		portInput.setTextFieldListener(new TextField.TextFieldListener() {
			@Override
			public void keyTyped(TextField textField, char c) {
				System.out.println("char: "+(int)c);
			}
		});

		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
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
		portInput.setPosition(width / 2 - width / 8, height / 2);
	}

}
