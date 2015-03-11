package com.mygdx.game.gui;

/**
 * Created by Eplemaskin on 11/03/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.Game;
import com.badlogic.gdx.graphics.GL20;

public class GameView implements Screen{
    private Stage stage;
    MyGdxGame game;

    public GameView(MyGdxGame game){
        stage = new Stage();
        this.game = game;

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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
