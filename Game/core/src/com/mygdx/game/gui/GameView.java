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
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.controller.AmmoChangeController;
import com.mygdx.game.controller.FireController;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.TextureManager;

import java.util.ArrayList;

public class GameView extends AbstractView implements Screen{

    MyGdxGame game;
    private Stage stage;

    private Environment environment;
    private Tank tank;

    private PolygonSprite ground;
    private PolygonSpriteBatch polyBatch;
    private Texture textureGround;

    private SpriteBatch batch;

    private HorizontalGroup groupTop;
    private HorizontalGroup groupBottom;

    private Skin menuSkin;
    private Skin fireSkin;
    private Skin ammoSkin;
    private Skin arrowLeftSkin;
    private Skin arrowRightSkin;
    private TextButton buttonMainMenu;
    private TextButton buttonFire;
    private TextButton buttonAmmo;
    private ImageButton arrowLeft;
    private ImageButton arrowRight;


    private OrthographicCamera camera;
    public GameView(MyGdxGame game){


        this.game = game;

        stage = new Stage();
        batch = new SpriteBatch();
        polyBatch = new PolygonSpriteBatch();

        textureGround = TextureManager.grass;
        environment = new Environment(2, 10);

        tank = new Tank(environment);
        tank.setPosition(new Vector2(Gdx.graphics.getWidth()/3, environment.getGroundHeight(Gdx.graphics.getWidth()/3)));


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
        arrowLeftSkin = new Skin(Gdx.files.internal("skins/arrowLeft.json"), new TextureAtlas(Gdx.files.internal("skins/leftArrow.pack")));
        arrowLeft = new ImageButton(arrowLeftSkin);
        arrowRightSkin = new Skin(Gdx.files.internal("skins/arrowRight.json"), new TextureAtlas(Gdx.files.internal("skins/rightArrow.pack")));
        arrowRight = new ImageButton(arrowRightSkin);
        setupCamera();

    }

    private void setupCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    @Override
    public void show() {

        buttonAmmo.addListener(new AmmoChangeController(this));

        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
            }
        });

        buttonFire.addListener(new FireController(this));

        /*buttonAmmo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Change ammunition");

            }
        });
        buttonAmmo.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown=true;
                //do your stuff
                //it will work when finger is released..
                System.out.println("Change ammunition");

            }

            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                boolean touchdown=false;
                //do your stuff it will work when u touched your actor
                return true;
            }

        });*/


        groupTop.top();
        groupTop.addActor(buttonMainMenu);
        groupTop.setFillParent(true);
        groupBottom.bottom();
        groupBottom.addActor(buttonAmmo);
        groupBottom.addActor(buttonFire);
        groupBottom.addActor(arrowLeft);
        groupBottom.addActor(arrowRight);
        groupBottom.setFillParent(true);

        stage.addActor(groupTop);
        stage.addActor(groupBottom);

        Gdx.input.setInputProcessor(stage);

    }



    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor((float) (198.0 / 255.0), (float) (226.0 / 255.0), 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        generateGround();

        batch.begin();
        batch.draw(tank.getTexture(), tank.getPosition().x, tank.getPosition().y);
        tank.render(batch);
        batch.end();

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

}
