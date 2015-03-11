package com.mygdx.game.gui;

/**
 * Created by Eplemaskin on 11/03/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameView implements Screen{
    private Stage stage;
    SpriteBatch batch;
    MyGdxGame game;
    ShapeRenderer shapeRenderer = new ShapeRenderer();


    int height = Gdx.graphics.getHeight();

    public GameView(MyGdxGame game){
        stage = new Stage();
        this.game = game;
        batch = new SpriteBatch();

    }


    public void generateBackground(){
        Gdx.gl.glClearColor((float)(198.0/255.0), (float)(226.0/255.0), 1, 1);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(50, 50, 50, 50);
        for (int i = 0; i < Gdx.graphics.getWidth() - 1; i++){

            shapeRenderer.setColor((float)(160.0/255.0), (float)(219.0/255.0), (float)(142.0/255.0), 1);
            shapeRenderer.rect(i, 50, 50, 50);

        }
        shapeRenderer.end();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        generateBackground();
        batch.end();

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
