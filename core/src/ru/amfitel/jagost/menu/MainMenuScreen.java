package ru.amfitel.jagost.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.amfitel.jagost.server.GameServer;

/**
 * Created by st_ni on 11.12.2016.
 */
public class MainMenuScreen implements Screen {

    Skin skin;
    Stage stage;
    TextButton startNewServerButton;
    TextButton joinGameButton;
    TextButton exitGameButton;
    final Game game;

    public MainMenuScreen(final Game game) {
        this.game = game;

        //Gdx.graphics.getWidth(), Gdx.graphics.getHeight()
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);// Make the stage consume events
        skin = BasicSkin.getSkin(TextButton.class);

        startNewServerButton = new TextButton("Start new server", skin); // Use the initialized skin
        stage.addActor(startNewServerButton);

        joinGameButton = new TextButton("Join game", skin); // Use the initialized skin
        stage.addActor(joinGameButton);

        exitGameButton = new TextButton("Exit", skin); // Use the initialized skin
        stage.addActor(exitGameButton);

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

        startNewServerButton.setDisabled(!GameServer.getInstance().isCanStart());
        joinGameButton.setDisabled(false);

        stage.act();
        stage.draw();

        if (Gdx.input.isTouched()) {
            if (exitGameButton.isPressed()) {
                Gdx.app.exit();
            }
            if (!startNewServerButton.isDisabled() && startNewServerButton.isPressed()) {
                game.setScreen(new StartNewServer(game));
                this.dispose();
            }
            if (!joinGameButton.isDisabled() && joinGameButton.isPressed()) {
                //todo
            }
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
        startNewServerButton.setPosition(width / 2 - width / 8, height / 2);
        joinGameButton.setPosition(width / 2 - width / 8, height / 2 - 80);
        exitGameButton.setPosition(width / 2 - width / 8, height / 2 - 160);
    }
}
