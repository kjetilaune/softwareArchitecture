package com.mygdx.game.gui;

/**
 * Created by Eplemaskin on 11/03/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.controller.AmmoChangeController;
import com.mygdx.game.controller.AngleController;
import com.mygdx.game.controller.FireController;
import com.mygdx.game.controller.MovementController;
import com.mygdx.game.model.Enums.Team;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.TextureManager;
import com.mygdx.game.model.Vehicle;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameView extends AbstractView implements Screen, Observer{

    MyGdxGame game;
    private Stage stage;

    private MovementController moveCtrl; // controls the left/right-movement of the tank

    private PolygonSprite ground; // ground is made up of polygons
    private PolygonSpriteBatch polyBatch; // to draw the ground

    private SpriteBatch batch; // to draw sprites: ex. tanks and barrels

    private HorizontalGroup groupTop; // the top menu
    private HorizontalGroup groupBottom; // the button at the bottom: fire, change ammmo etc

    // for menu and buttons
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

    public Game gameInstance;
    public Environment environment; // the current environment of the game
    public Player currentPlayer; // current player
    public Vehicle currentVehicle; // current player's vehicle
    public ArrayList<Player> players;

    public boolean isFiring = false; // is a bullet being fired?



    private OrthographicCamera camera;


    Label labelCurrentPlayer;
    Label labelChosenAmmo;


    public GameView(MyGdxGame game, Game gameInstance){

        this.game = game; // the application launcher

        // store the needed variables
        this.gameInstance = gameInstance; // the current game
        environment = gameInstance.getEnvironment();
        currentPlayer = gameInstance.getCurrentPlayer();
        currentVehicle = currentPlayer.getVehicle();
        players = gameInstance.getPlayers();

        // instantiate what is used to draw
        stage = new Stage();
        batch = new SpriteBatch();
        polyBatch = new PolygonSpriteBatch();

        // instantiate controllers
        moveCtrl = new MovementController(this);

        // instantiate menu stuff
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
        arrowLeft.setName("arrowLeft");
        arrowRightSkin = new Skin(Gdx.files.internal("skins/arrowRight.json"), new TextureAtlas(Gdx.files.internal("skins/rightArrow.pack")));
        arrowRight = new ImageButton(arrowRightSkin);
        arrowRight.setName("arrowRight");


        setupCamera(); // set up the camera
        testGUI(); // just testing out stuff for menu

    }

    public void testGUI() {

        menuSkin.getFont("font").setScale(2f);
        labelCurrentPlayer = new Label(currentPlayer.getTeam().getName(), fireSkin);
        labelChosenAmmo = new Label("Chosen\n ammo:", fireSkin);



    }



    private void setupCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }


    // is called once in the beginning. creates the menu and buttons, and adds listeners to buttons.
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

        stage.addListener(new AngleController(this));

        arrowLeft.addListener(moveCtrl);
        arrowRight.addListener(moveCtrl);


        groupTop.top();
        //groupTop.addActor(buttonMainMenu);
        //groupTop.addActor(labelCurrentPlayer);
        //groupTop.addActor(labelChosenAmmo);
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


    // is called all the time. draws the game with different positions and values for tanks etc.
    @Override
    public void render(float delta) {

        currentPlayer = gameInstance.getCurrentPlayer();
        currentVehicle = currentPlayer.getVehicle();

        // set background
        Gdx.gl.glClearColor(environment.getBgColors()[0], environment.getBgColors()[1], environment.getBgColors()[2], environment.getBgColors()[3]);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw the ground using the set environment
        generateGround();

        // draws the sprites, ex vehicles etc.
        batch.begin();

        for (Player p : players) {
            batch.draw(new TextureRegion(p.getVehicle().getTexture()), p.getVehicle().getPosition().x, p.getVehicle().getPosition().y, 0, 0, (float)TextureManager.tank.getWidth(), (float)TextureManager.tank.getHeight(), 1, 1, p.getVehicle().getRotation());
            batch.draw(new TextureRegion(((Tank)p.getVehicle()).getBarrel().getTexture()), ((Tank)p.getVehicle()).getBarrel().getPosition().x, ((Tank)p.getVehicle()).getBarrel().getPosition().y, 0, TextureManager.barrel.getHeight()/2, (float)TextureManager.barrel.getWidth(), (float)TextureManager.barrel.getHeight(), 1, 1, ((Tank)p.getVehicle()).getBarrel().getRotation() + ((Tank)p.getVehicle()).getBarrel().getAngle());
        }

        //batch.draw(new TextureRegion(currentVehicle.getTexture()), currentVehicle.getPosition().x, currentVehicle.getPosition().y, 0, 0, (float)TextureManager.tank.getWidth(), (float)TextureManager.tank.getHeight(), 1, 1, currentVehicle.getRotation());
        //batch.draw(new TextureRegion(((Tank)currentVehicle).getBarrel().getTexture()), ((Tank)currentVehicle).getBarrel().getPosition().x, ((Tank)currentVehicle).getBarrel().getPosition().y, 0, TextureManager.barrel.getHeight()/2, (float)TextureManager.barrel.getWidth(), (float)TextureManager.barrel.getHeight(), 1, 1, ((Tank)currentVehicle).getBarrel().getRotation() + ((Tank)currentVehicle).getBarrel().getAngle());

        // generates the menu
        generateMenu();

        // NOT FINISHED: should draw the bullet if a tank is firing

        if (currentPlayer.getChosenAmmo().getPosition() != null) {

            //System.out.println("drawing bullet...");

            Texture ammoTexture = currentPlayer.getTeam().getAmmunitionTexture(currentPlayer.getChosenAmmo().getName());
            batch.draw(new TextureRegion(ammoTexture), currentPlayer.getChosenAmmo().getPosition().x, currentPlayer.getChosenAmmo().getPosition().y);

        }

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

    // draws the ground from the set environment
    private void generateGround() {

        polyBatch.begin();

        ArrayList<Polygon> polys = environment.getPolygons();

        for (Polygon p : polys) {
            float[] vecs = p.getVertices();

            short[] triangles = new EarClippingTriangulator().computeTriangles(vecs).toArray();
            PolygonRegion region = new PolygonRegion(new TextureRegion(environment.getTexture()), vecs, triangles);
            ground = new PolygonSprite(region);

            ground.draw(polyBatch);
        }

        polyBatch.end();

    }

    // generates the menu
    private void generateMenu() {

        BitmapFont font = new BitmapFont();
        batch.draw(new TextureRegion(TextureManager.menu), 0, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 7, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        String textCurrentPlayer = currentPlayer.getTeam().getName();
        String textChosenAmmo = "Chosen ammo: " + currentPlayer.getChosenAmmo().getName();
        String textLeftAmmo = "Ammo left: " + currentPlayer.getInventory().getAmmoLeft(currentPlayer.getChosenAmmo().getName());

        font.setScale(4f);
        font.draw(batch, textCurrentPlayer, 0, Gdx.graphics.getHeight());
        font.setScale(3.5f);
        font.draw(batch, textChosenAmmo + "\t" + textLeftAmmo, 0, Gdx.graphics.getHeight() - font.getBounds(textCurrentPlayer).height - 10);



    }

    public void update(Observable o, Object arg){

        //System.out.println("IT CHANGED");
        arg.toString();
    }


}
