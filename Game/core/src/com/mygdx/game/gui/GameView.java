package com.mygdx.game.gui;

/**
 * Created by Eplemaskin on 11/03/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.controller.AmmoChangeController;
import com.mygdx.game.controller.AngleController;
import com.mygdx.game.controller.FireController;
import com.mygdx.game.controller.MovementController;
import com.mygdx.game.model.AmmoTypes.Bullet;
import com.mygdx.game.model.BulletPhysics;
import com.mygdx.game.model.Enums.Team;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.TextureManager;
import com.mygdx.game.model.Vehicle;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameView extends AbstractView implements Screen, Observer{

    MyGdxGame game;
    private Stage stage;
    private GameView gameView;

    private ShapeRenderer shapeRenderer;

    private MovementController moveCtrl; // controls the left/right-movement of the tank

    private PolygonSprite ground; // ground is made up of polygons
    private PolygonSpriteBatch polyBatch; // to draw the ground

    private SpriteBatch batch; // to draw sprites: ex. tanks and barrels

    private Table groupTop;
    private Table groupBottom; // the button at the bottom: fire, change ammmo etc
    private Table groupRight;
    private Table groupLeft;

    // for menu and buttons
    private Skin menuSkin;
    private Skin fireSkin;

    private Skin arrowLeftSkin;
    private Skin arrowRightSkin;
    private TextButton buttonMainMenu;
    private TextButton buttonFire;
    private TextButton buttonAmmo;
    private TextButton buttonStore;
    private ImageButton arrowLeft;
    private ImageButton arrowRight;

    private BitmapFont font;

    // Mikal: Why are these public? Should be private and have getters and setters.
    // Annie: Because of the controllers. I'm not sure if a view should have getters and setters, should check how it should be
    public Game gameInstance;
    public Environment environment; // the current environment of the game
    public Player currentPlayer; // current player
    public Vehicle currentVehicle; // current player's vehicle
    public ArrayList<Player> playersAlive;

    public boolean isFiring = false; // is a bullet being fired?


    private OrthographicCamera camera;

    private Label labelRound;
    private Label labelCurrentPlayer;
    private Label labelTurn;
    private Label labelChosenAmmo;
    private Label labelLeftAmmo;
    private Label labelHealthLeft;
    private Label labelFuelLeft;
    private Label labelPower;

    private Sprite spriteChosenAmmo;
    private Sprite spriteCloudFront;
    private Sprite spriteCloudBack;
    private Sprite spriteSky;

    public Music battleSong;


    public GameView(MyGdxGame game, Game gameInstance){

        this.game = game; // the application launcher
        gameView = this;
        game.introSong.stop();
        shapeRenderer = new ShapeRenderer();

        // store the needed variables
        this.gameInstance = gameInstance; // the current game
        environment = gameInstance.getEnvironment();
        currentPlayer = gameInstance.getCurrentPlayer();
        currentVehicle = currentPlayer.getVehicle();
        playersAlive = gameInstance.getPlayersAlive();

        // instantiate what is used to draw
        stage = new Stage();
        batch = new SpriteBatch();
        polyBatch = new PolygonSpriteBatch();

        // instantiate controllers
        moveCtrl = new MovementController(this);

        // instantiate menu stuff
        groupTop  = new Table();
        groupBottom = new Table();
        groupRight = new Table();
        groupLeft = new Table();

        menuSkin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        menuSkin.getFont("font").scale(1);
        buttonMainMenu = new TextButton("To Main Menu", menuSkin);
        fireSkin = new Skin(Gdx.files.internal("skins/fire.json"), new TextureAtlas(Gdx.files.internal("skins/fire.pack")));
        fireSkin.getFont("font").scale(1);
        fireSkin.getFont("font").setScale(2);
        buttonFire = new TextButton("Fire", fireSkin);
        buttonAmmo = new TextButton("Ammo", menuSkin);
        arrowLeftSkin = new Skin(Gdx.files.internal("skins/arrowLeft.json"), new TextureAtlas(Gdx.files.internal("skins/leftArrow.pack")));
        arrowLeft = new ImageButton(arrowLeftSkin);
        arrowLeft.setName("arrowLeft");
        arrowRightSkin = new Skin(Gdx.files.internal("skins/arrowRight.json"), new TextureAtlas(Gdx.files.internal("skins/rightArrow.pack")));
        arrowRight = new ImageButton(arrowRightSkin);
        arrowRight.setName("arrowRight");
        buttonStore = new TextButton("Store", menuSkin);
        buttonStore.setName("Store");

        font = new BitmapFont(Gdx.files.internal("font/fireBold.fnt"));

        spriteChosenAmmo = currentPlayer.getTeam().getAmmunitionSprite(currentPlayer.getChosenAmmo().getName());
        spriteChosenAmmo.scale(0.3f);

        spriteCloudFront = new Sprite(TextureManager.cloudsForeground);
        spriteCloudBack = new Sprite(TextureManager.cloudsBackground);
        spriteSky = new Sprite(TextureManager.skyBackground);
        spriteCloudFront.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/3);
        spriteCloudFront.setPosition(0, 2 * Gdx.graphics.getHeight() / 3);
        spriteCloudBack.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/3);
        spriteCloudBack.setPosition(0, 2*Gdx.graphics.getHeight()/3);
        spriteSky.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        battleSong = Gdx.audio.newMusic(Gdx.files.internal("Music/battleMusic.MP3"));

        setupCamera(); // set up the camera

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
                game.setScreen(new MainMenu(game, 0));
            }
        });

        buttonStore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StoreView(game, gameInstance, gameView));
            }
        });

        buttonFire.addListener(new FireController(this));

        stage.addListener(new AngleController(this));

        arrowLeft.addListener(moveCtrl);
        arrowRight.addListener(moveCtrl);


        fireSkin.getFont("font").setScale(1.0f);


        labelRound = new Label(String.format("Round %d out of %d", gameInstance.getCurrentRound(), gameInstance.getNumberOfRounds()), fireSkin);
        labelCurrentPlayer = new Label(String.format("Player %d - %s", currentPlayer.getPlayerNumber(), currentPlayer.getTeam().getName()), fireSkin);
        labelCurrentPlayer.setFontScale(2);
        labelTurn = new Label("Turns left: " + (gameInstance.getNumberOfTurns() - currentPlayer.getTurnsTaken()), fireSkin);
        //fireSkin.getFont("font").setScale(1f);
        labelChosenAmmo = new Label("Chosen ammo: " + currentPlayer.getChosenAmmo().getName(), fireSkin);
        labelLeftAmmo = new Label("Ammo left: " + currentPlayer.getInventory().getAmmoLeft(currentPlayer.getChosenAmmo().getName()), fireSkin);
        labelHealthLeft = new Label("Health: " + (currentPlayer.getVehicle().getHealth() + currentPlayer.getHealthUpgrade()), fireSkin);
        labelFuelLeft = new Label("Fuel: " + (currentPlayer.getVehicle().getFuel() + currentPlayer.getFuelUpgrade()), fireSkin);
        labelPower = new Label("Power: " + currentPlayer.getVehicle().getPower(), fireSkin);

        labelRound.setColor(Color.BLACK);
        labelCurrentPlayer.setColor(Color.BLACK);
        labelTurn.setColor(Color.BLACK);
        labelChosenAmmo.setColor(Color.BLACK);
        labelLeftAmmo.setColor(Color.BLACK);
        labelHealthLeft.setColor(Color.BLACK);
        labelFuelLeft.setColor(Color.BLACK);
        labelPower.setColor(Color.BLACK);

        groupTop.left().top();
        groupTop.defaults();
        groupTop.add(labelCurrentPlayer).pad(10, 100, 10, 0).fillX();
        groupTop.add(labelRound).pad(10, 10, 10, 0).fillX();
        groupTop.add(labelTurn).pad(10, 10, 10, 0).fillX().row();
        groupTop.add(labelChosenAmmo).padLeft(10).fillX();
        groupTop.add(labelLeftAmmo).padLeft(40).fillX();
        groupTop.add(labelHealthLeft).padLeft(40).fillX();
        groupTop.add(labelFuelLeft).padLeft(40).fillX();
        groupTop.add(labelPower).padLeft(40).fillX();
        groupTop.setFillParent(true);

        groupBottom.bottom();
        groupBottom.add(buttonFire);

        groupLeft.bottom().left();
        groupLeft.add(arrowLeft).padLeft(20).padBottom(20);
        groupLeft.add(arrowRight).padLeft(20).padBottom(20);

        groupRight.bottom().right();
        groupRight.add(buttonAmmo).padBottom(20);
        groupRight.add(buttonStore).padBottom(20);

        groupBottom.setFillParent(true);
        groupRight.setFillParent(true);
        groupLeft.setFillParent(true);

        stage.addActor(groupTop);
        stage.addActor(groupBottom);
        stage.addActor(groupRight);
        stage.addActor(groupLeft);

        battleSong.play();
        battleSong.setLooping(true);

        Gdx.input.setInputProcessor(stage);

    }


    // is called all the time. draws the game with different positions and values for tanks etc.
    @Override
    public void render(float delta) {

        // if the round is finished (either because there is only one player left, or there is no more turns left
        /*if (gameInstance.getRoundWinner() != null) {
            if (gameInstance.getRoundsLeft() == 0) {
                game.setScreen(new GameOverView(game, gameInstance));
                battleSong.stop();
            } else {
                game.setScreen(new RoundOverView(game, gameInstance.getRoundWinner(), gameInstance, this));
                battleSong.stop();
            }
        }*/

        if (gameInstance.getRoundWinners() != null) {
            battleSong.stop();
            if (gameInstance.getRoundsLeft() == 0) {
                game.setScreen(new GameOverView(game, gameInstance));
            } else {
                game.setScreen(new RoundOverView(game, gameInstance, this));
            }
            this.dispose();
        }

        environment = gameInstance.getEnvironment();
        currentPlayer = gameInstance.getCurrentPlayer();
        currentVehicle = currentPlayer.getVehicle();

        // set background
        Gdx.gl.glClearColor(environment.getBgColors()[0], environment.getBgColors()[1], environment.getBgColors()[2], environment.getBgColors()[3]);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        spriteSky.draw(batch);
        spriteCloudBack.draw(batch);
        batch.end();


        // draw the ground using the set environment
        generateGround();


        // draws the sprites, ex vehicles etc.
        batch.begin();

        try {
            for (Player p : playersAlive) {
                if (p.getVehicle().getHealth() > 0) {

                    //Draw the barrel and vehicle
                    batch.draw(new TextureRegion(p.getVehicle().getBarrel().getTexture()), p.getVehicle().getBarrel().getPosition().x, p.getVehicle().getBarrel().getPosition().y, 0, (float) p.getVehicle().getBarrel().getTexture().getHeight() / 2, (float) p.getVehicle().getBarrel().getTexture().getWidth(), (float) p.getVehicle().getBarrel().getTexture().getHeight(), 1, 1, p.getVehicle().getBarrel().getRotation() + p.getVehicle().getBarrel().getAngle());
                    batch.draw(new TextureRegion(p.getVehicle().getTexture()), p.getVehicle().getPosition().x, p.getVehicle().getPosition().y, 0, 0, (float) p.getVehicle().getTexture().getWidth(), (float) p.getVehicle().getTexture().getHeight(), 1, 1, p.getVehicle().getRotation());

                    font.setColor(Color.BLACK);
                    batch.end();

                    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                    shapeRenderer.setColor(0, 0, 0, 1);
                    shapeRenderer.rect(p.getVehicle().getPosition().x + p.getVehicle().getRelativeWidth() / 2, p.getVehicle().getPosition().y + p.getVehicle().getRelativeHeight(), 102, stage.getHeight() / 40 + 2);
                    shapeRenderer.end();

                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

                    if (p.getVehicle().getHealth() < 25) {
                        shapeRenderer.setColor(249.f / 255, 22.f / 255, 39.f / 255, 1);
                    } else if (p.getVehicle().getHealth() < 50) {
                        shapeRenderer.setColor(255.f / 255, 192.f / 255, 76.f / 255, 1);
                    } else if (p.getVehicle().getHealth() < 75) {
                        shapeRenderer.setColor(255.f / 255, 255.f / 255, 127.f / 255, 1);
                    } else {
                        shapeRenderer.setColor(193.f / 255, 255.f / 255, 139.f / 255, 1);
                    }

                    shapeRenderer.rect(p.getVehicle().getPosition().x + p.getVehicle().getRelativeWidth() / 2, p.getVehicle().getPosition().y + p.getVehicle().getRelativeHeight(), p.getVehicle().getHealth(), stage.getHeight() / 40);
                    shapeRenderer.end();
                    batch.begin();
                    font.draw(batch, Integer.toString(p.getVehicle().getHealth()), p.getVehicle().getPosition().x + p.getVehicle().getRelativeWidth() / 2, p.getVehicle().getPosition().y + p.getVehicle().getRelativeHeight());

                }
            }
        }
        catch (Exception ConcurrentModificationException) {
            System.out.println("error, killed player while looping");
        }


        // draw the bullet if a tank is firing

        if (currentPlayer.getChosenAmmo().getPosition() != null) {

            Texture ammoTexture = currentPlayer.getTeam().getAmmunitionTexture(currentPlayer.getChosenAmmo().getName());
            batch.draw(new TextureRegion(ammoTexture), currentPlayer.getChosenAmmo().getPosition().x, currentPlayer.getChosenAmmo().getPosition().y);

        }

        spriteCloudFront.draw(batch);

        batch.end();

        //Personal fuel box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        if (currentPlayer.getVehicle().getFuel() < 25){
            shapeRenderer.setColor(249.f / 255, 22.f / 255, 39.f / 255, 1);
        }
        else if (currentPlayer.getVehicle().getFuel() < 50){
            shapeRenderer.setColor(255.f / 255, 192.f / 255, 76.f / 255, 1);
        }
        else if (currentPlayer.getVehicle().getFuel() < 75){
            shapeRenderer.setColor(255.f / 255, 255.f / 255, 127.f / 255, 1);
        }
        else{
            shapeRenderer.setColor(193.f / 255, 255.f / 255, 139.f / 255, 1);
        }

        shapeRenderer.rect(stage.getWidth()/40 * 23, stage.getHeight()/40 * 35, currentPlayer.getVehicle().getFuel(), stage.getHeight()/40);
        shapeRenderer.end();

        //Personal fuel outline box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(stage.getWidth()/40 * 23, stage.getHeight() / 40 * 35, 102, stage.getHeight()/40 + 2);
        shapeRenderer.end();

        //Personal health box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        if (currentPlayer.getVehicle().getHealth() < 25){
            shapeRenderer.setColor(249.f / 255, 22.f / 255, 39.f / 255, 1);
        }
        else if (currentPlayer.getVehicle().getHealth() < 50){
            shapeRenderer.setColor(255.f / 255, 192.f / 255, 76.f / 255, 1);
        }
        else if (currentPlayer.getVehicle().getHealth() < 75){
            shapeRenderer.setColor(255.f / 255, 255.f / 255, 127.f / 255, 1);
        }
        else{
            shapeRenderer.setColor(193.f / 255, 255.f / 255, 139.f / 255, 1);
        }

        shapeRenderer.rect(stage.getWidth()/20 * 9, stage.getHeight()/40 * 35, currentPlayer.getVehicle().getHealth(), stage.getHeight()/40);
        shapeRenderer.end();

        //Personal health outline box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(stage.getWidth()/20 * 9, stage.getHeight() / 40 * 35, 102, stage.getHeight()/40 + 2);
        shapeRenderer.end();

        //Fire power outline box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(stage.getWidth()/20 * 17, stage.getHeight() / 20 * 18, 202, stage.getHeight()/30 + 2);
        shapeRenderer.end();

        //Fire power fluctuator
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(249.f / 255, 22.f / 255, 39.f / 255, 1);
        shapeRenderer.rect(stage.getWidth()/20 * 17, stage.getHeight() / 20 * 18, currentPlayer.getVehicle().getPower() * 2, stage.getHeight()/30);
        shapeRenderer.end();

        stage.act();
        stage.draw();

        updateTopMenu();


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



    private void updateTopMenu() {

        /*labelRound.setColor(Color.BLACK);
        labelCurrentPlayer.setColor(Color.BLACK);
        labelTurn.setColor(Color.BLACK);
        labelChosenAmmo.setColor(Color.BLACK);*/

        labelRound.setText(String.format("Round %d out of %d", gameInstance.getCurrentRound(), gameInstance.getNumberOfRounds()));
        labelCurrentPlayer.setText(String.format("Player %d - %s", currentPlayer.getPlayerNumber(), currentPlayer.getTeam().getName()));
        labelTurn.setText("Turns left: " + (gameInstance.getNumberOfTurns() - currentPlayer.getTurnsTaken()));
        labelChosenAmmo.setText("Chosen ammo: " + currentPlayer.getChosenAmmo().getName());
        labelLeftAmmo.setText("Ammo left: " + currentPlayer.getInventory().getAmmoLeft(currentPlayer.getChosenAmmo().getName()));
        labelHealthLeft.setText("Health: " + (currentPlayer.getVehicle().getHealth() + currentPlayer.getHealthUpgrade()));
        labelFuelLeft.setText("Fuel: " + (currentPlayer.getVehicle().getFuel() + currentPlayer.getFuelUpgrade()));
        labelPower.setText("Power: " + currentPlayer.getVehicle().getPower());

    }

    // draws the ground from the set environment
    private void generateGround() {

        polyBatch.begin();

        //ArrayList<Polygon> polys = environment.getPolygons();
        ArrayList<Polygon> polys = gameInstance.getEnvironment().getPolygons();

        for (Polygon p : polys) {
            float[] vecs = p.getVertices();

            short[] triangles = new EarClippingTriangulator().computeTriangles(vecs).toArray();
            PolygonRegion region = new PolygonRegion(new TextureRegion(environment.getTexture()), vecs, triangles);
            ground = new PolygonSprite(region);

            ground.draw(polyBatch);
        }

        polyBatch.end();

    }


    public void update(Observable o, Object arg){

        //System.out.println("IT CHANGED");
        arg.toString();
    }

    public void setIsFiring(boolean isFiring) {
        this.isFiring = isFiring;

        if (isFiring == true) {
            suspendButtons();
        }
        else {
            resumeButtons();
        }
    }

    public void suspendButtons() {
        buttonAmmo.setVisible(false);
        buttonStore.setVisible(false);
        buttonFire.setVisible(false);
        arrowLeft.setVisible(false);
        arrowRight.setVisible(false);
    }

    public void resumeButtons() {
        buttonAmmo.setVisible(true);
        buttonStore.setVisible(true);
        buttonFire.setVisible(true);
        arrowLeft.setVisible(true);
        arrowRight.setVisible(true);
    }

}
