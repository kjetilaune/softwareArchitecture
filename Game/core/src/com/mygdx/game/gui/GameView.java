package com.mygdx.game.gui;

/**
 * Created by Eplemaskin on 11/03/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameView implements Screen{

    MyGdxGame game;
    private Stage stage;

    private PolygonSprite ground;
    PolygonSpriteBatch polyBatch;
    Texture textureGround;

    SpriteBatch batch;

    ShapeRenderer shapeRenderer = new ShapeRenderer();


    private OrthographicCamera camera;
    public GameView(MyGdxGame game){

        this.game = game;

        stage = new Stage();
        batch = new SpriteBatch();
        polyBatch = new PolygonSpriteBatch();

        textureGround = new Texture("grasstexture.png");

        setupCamera();

    }

    private void setupCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
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

        polyBatch.begin();
        generateGround();
        ground.draw(polyBatch);
        polyBatch.end();

        batch.begin();
        //generateBackground();
        //batch.draw(ground, ground.getX(), ground.getY());
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

    private void generateGround() {

        float[] vecs = {0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2, 0, 0, 0};
        short[] triangles = new EarClippingTriangulator().computeTriangles(vecs).toArray();
        PolygonRegion region = new PolygonRegion(new TextureRegion(textureGround), vecs, triangles);
        ground = new PolygonSprite(region);

    }

    private void generateBackground(){
        Gdx.gl20.glLineWidth(2);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor((float)(160.0/255.0), (float)(219.0/255.0), (float)(142.0/255.0), 1);

        float[] v = {0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2, 0, 0, 0};

        shapeRenderer.polygon(v);

        /*for (int i = 0; i < Gdx.graphics.getWidth() - 1; i++){
            shapeRenderer.line(i, Gdx.graphics.getHeight(), i, Gdx.graphics.getHeight() - Math.round(i*0.1) - 400);
        }*/
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
}
