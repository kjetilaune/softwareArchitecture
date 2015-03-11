package com.mygdx.game.gui;

/**
 * Created by Eplemaskin on 11/03/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.model.Game;

public class GameView implements Screen{
    private Stage stage;

    public GameView(){
        stage = new Stage();

    }

    public void generateBackground(){
        Gdx.gl.glClearColor(0, 0, 1, 1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        generateBackground();
        stage.act();
        stage.draw();
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

    }
}
