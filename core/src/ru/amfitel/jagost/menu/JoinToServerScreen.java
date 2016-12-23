package ru.amfitel.jagost.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.amfitel.jagost.net.ClientEventHandler;
import ru.amfitel.jagost.net.GameClient;
import ru.amfitel.jagost.net.MessageInt;
import ru.amfitel.jagost.net.NetUtils;

/**
 * Created by estarcev on 16.12.2016.
 */
public class JoinToServerScreen implements Screen {

	private final Game game;
	Stage stage;
	Skin skin;
	private ClientEventHandler clientEventHandler;

	TextField serverIpTF;
	TextField userNameTF;
	TextButton joinGameButton;

	public JoinToServerScreen(final Game game) {
		this.game = game;

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		skin = BasicSkin.getSkin(TextField.class, TextButton.class, Window.class, Label.class);

		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();

		serverIpTF = new TextField(NetUtils.getOwnIpAddress(), skin);
		stage.addActor(serverIpTF);

		userNameTF = new TextField("User#"+Gdx.app.hashCode(), skin);
		stage.addActor(userNameTF);

		joinGameButton = new TextButton("Join", skin);
		stage.addActor(joinGameButton);
		joinGameButton.addListener(getTouchListener());

		createEventHandler();
		GameClient.getInstance().addEventHandler(clientEventHandler);
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
		GameClient.getInstance().removeEventHandler(clientEventHandler);
	}

	private EventListener getTouchListener() {
		return new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (event.getListenerActor() == joinGameButton && !joinGameButton.isDisabled()) {
					joinGameButton.setDisabled(true);
					String adr = serverIpTF.getText();

					GameClient.getInstance().joinToServer(adr);
				}
			}
		};
	}

	private void createEventHandler(){
		clientEventHandler = new ClientEventHandler() {
			@Override
			public void onOpen() {
				//TODO
				System.out.println("connected");
			}

			@Override
			public void onMessage(MessageInt message) {

			}

			@Override
			public void onClose() {
				joinGameButton.setDisabled(false);
				joinGameButton.setChecked(false);
				showDialog("closed");
			}

			@Override
			public void onError(Exception ex) {
				joinGameButton.setDisabled(false);
				joinGameButton.setChecked(false);
				showDialog(ex.getMessage());
			}
		};
	}

	private void showDialog(String str) {
		final Dialog dialog = new Dialog("Warning", skin) {
			@Override
			protected void result(Object object) {
				this.hide();
			}
		};
		dialog.text(str);
		dialog.button("Close");
		dialog.show(stage);
		dialog.setWidth(Gdx.graphics.getWidth() / 2);
		dialog.setHeight(Gdx.graphics.getHeight() / 2);
		dialog.center();

	}

	private void setActorsPositions(int width, int height) {
		serverIpTF.setPosition(width / 2 - width / 8,height-50);
		userNameTF.setPosition(width / 2 - width / 8,height-100);
		joinGameButton.setPosition(width / 2 - width / 8,height-150);

	}
}
