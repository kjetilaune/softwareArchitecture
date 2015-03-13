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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.TextureManager;

import java.util.ArrayList;

public class GameView implements Screen{

    MyGdxGame game;
    private Stage stage;

    private Environment environment;

    private PolygonSprite ground;
    private PolygonSpriteBatch polyBatch;
    private Texture textureGround;

    private SpriteBatch batch;

    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    //private Table table;
    private HorizontalGroup groupTop;
    private HorizontalGroup groupBottom;

    private Skin menuSkin;
    private Skin fireSkin;
    private Skin ammoSkin;
    private TextButton buttonMainMenu;
    private TextButton buttonFire;
    private TextButton buttonAmmo;


    private OrthographicCamera camera;
    public GameView(MyGdxGame game){


        this.game = game;

        stage = new Stage();
        batch = new SpriteBatch();
        polyBatch = new PolygonSpriteBatch();

        textureGround = TextureManager.grass;
        //environment = new Environment(2, 10);
        environment = new Environment();

        //table = new Table();
        groupTop  = new HorizontalGroup();
        groupBottom = new HorizontalGroup();

        menuSkin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        menuSkin.getFont("font").scale(1);
        buttonMainMenu = new TextButton("To Main Menu", menuSkin);
        fireSkin = new Skin(Gdx.files.internal("skins/fire.json"), new TextureAtlas(Gdx.files.internal("skins/fire.pack")));
        fireSkin.getFont("font").scale(1);
        buttonFire = new TextButton("Fire", fireSkin);
        ammoSkin = new Skin(Gdx.files.internal("skins/ammo.json"), new TextureAtlas(Gdx.files.internal("skins/ammo.pack")));
        ammoSkin.getFont("font").scale(1);
        buttonAmmo = new TextButton("Ammo", ammoSkin);


        setupCamera();

    }

    private void setupCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    @Override
    public void show() {

        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
            }
        });

        buttonFire.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("FIRE");
            }
        });

        buttonAmmo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Change ammunition");
            }
        });

        groupTop.top();
        groupTop.addActor(buttonMainMenu);
        groupTop.addActor(buttonAmmo);
        groupTop.addActor(buttonFire);
        groupTop.setFillParent(true);
        /*
        groupBottom.center();
        groupBottom.addActor(buttonAmmo);
        groupBottom.addActor(buttonFire);
        groupBottom.setFillParent(true);*/


        stage.addActor(groupTop);
        //stage.addActor(groupBottom);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor((float) (198.0 / 255.0), (float) (226.0 / 255.0), 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        generateGround();


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

        polyBatch.begin();

        ArrayList<Polygon> polys = environment.getPolygons();


        for (Polygon p : polys) {
            float[] vecs = p.getVertices();

            short[] triangles = new EarClippingTriangulator().computeTriangles(vecs).toArray();
            PolygonRegion region = new PolygonRegion(new TextureRegion(textureGround), vecs, triangles);
            ground = new PolygonSprite(region);

            ground.draw(polyBatch);

        }

        polyBatch.end();

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
