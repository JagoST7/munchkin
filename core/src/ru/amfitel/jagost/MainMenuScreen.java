package ru.amfitel.jagost;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ru.amfitel.jagost.menu.BasicSkin;

/**
 * Created by st_ni on 11.12.2016.
 */
public class MainMenuScreen implements Screen {

    Skin skin;
    Stage stage;
    TextButton newGameButton;
    TextButton exitGameButton;
    final Game game;

    public MainMenuScreen(final Game gam) {
        game = gam;
        Gdx.graphics.setContinuousRendering(false);
//        Gdx.graphics.requestRendering();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);// Make the stage consume events
        skin = BasicSkin.getSkin();

         newGameButton = new TextButton("New game", skin); // Use the initialized skin
        newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2);
        stage.addActor(newGameButton);

         exitGameButton = new TextButton("Exit", skin); // Use the initialized skin
        exitGameButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2 - 80);
        stage.addActor(exitGameButton);
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
            if(exitGameButton.isPressed()){
                Gdx.app.exit();
            }
            if(newGameButton.isPressed()){
                //todo
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        System.out.println(width + " " + height);
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
