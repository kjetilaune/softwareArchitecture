package com.mygdx.game.gui;

/**
 * Created by Eplemaskin on 11/03/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameView implements Screen{
    private Stage stage;
    SpriteBatch batch;
    MyGdxGame game;
    ShapeRenderer shapeRenderer = new ShapeRenderer();


    private OrthographicCamera camera;

    public GameView(MyGdxGame game){
        stage = new Stage();
        this.game = game;
        batch = new SpriteBatch();

        camera= new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

    }


    private void generateBackground(){
        Gdx.gl20.glLineWidth(2);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor((float)(160.0/255.0), (float)(219.0/255.0), (float)(142.0/255.0), 1);

        for (int i = 0; i < Gdx.graphics.getWidth() - 1; i++){
            shapeRenderer.line(i, Gdx.graphics.getHeight(), i, Gdx.graphics.getHeight() - Math.round(i*0.1) - 400);
        }
        shapeRenderer.end();
    }

    // fix this later if time
    private void generateHill(int width, int height, int startX) {

        for (int i = startX ; i < width ; i++) {
            if (i < width/5) {
                shapeRenderer.line(i, Gdx.graphics.getHeight(), i, Gdx.graphics.getHeight() - height/5);
            }
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor((float)(198.0/255.0), (float)(226.0/255.0), 1, 1);
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        batch.begin();
        generateBackground();
        batch.end();

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
