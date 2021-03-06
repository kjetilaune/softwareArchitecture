package com.mygdx.game.view;

/**
 * Created by Eplemaskin on 11/03/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.AudioVisualManagers.TextureManager;
import com.mygdx.game.model.Vehicle;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameView implements Screen, Observer{

    MyGdxGame game;
    private Stage stage;
    private GameView gameView;

    private ShapeRenderer shapeRenderer;

    private MovementController moveCtrl; // controls the left/right-movement of the tank

    private PolygonSprite ground; // ground is made up of polygons
    private PolygonSpriteBatch polyBatch; // to draw the ground

    private SpriteBatch batch; // to draw sprites: ex. tanks and barrels

    private Table groupTop1;
    private Table groupTop2;
    private Table groupTopRight;
    private Table groupBottom; // the button at the bottom: fire, change ammmo etc
    private Table groupRight;
    private Table groupLeft;

    // for menu and buttons
    private Skin menuSkin;
    private Skin fireSkin;
    private Skin fireBSkin;

    private Skin arrowLeftSkin;
    private Skin arrowRightSkin;
    private Skin windowSkin;
    private TextButton buttonMainMenu;
    private TextButton buttonFire;
    private TextButton buttonAmmo;
    private TextButton buttonExit;
    private ImageButton arrowLeft;
    private ImageButton arrowRight;

    private BitmapFont font;

    private Game gameInstance;
    private Environment environment; // the current environment of the game
    private Player currentPlayer; // current player
    private Vehicle currentVehicle; // current player's vehicle
    private ArrayList<Player> playersAlive;

    private boolean isFiring = false; // is a bullet being fired?


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

    private Music battleSong;


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
        moveCtrl = new MovementController(gameInstance);

        // instantiate menu stuff
        groupTop1 = new Table();
        groupTop2 = new Table();
        groupTopRight = new Table();
        groupBottom = new Table();
        groupRight = new Table();
        groupLeft = new Table();

        menuSkin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        menuSkin.getFont("font").scale((float)(Gdx.graphics.getHeight())/1080);
        buttonMainMenu = new TextButton("To Main Menu", menuSkin);
        fireSkin = new Skin(Gdx.files.internal("skins/fire.json"), new TextureAtlas(Gdx.files.internal("skins/fire.pack")));
        fireBSkin = new Skin(Gdx.files.internal("skins/fire.json"), new TextureAtlas(Gdx.files.internal("skins/fire.pack")));
        fireBSkin.getFont("font").setScale((float)(Gdx.graphics.getHeight())/540);
        fireSkin.getFont("font").scale((float)(Gdx.graphics.getHeight())/1080);
        fireSkin.getFont("font").scale((float)(Gdx.graphics.getHeight())/540);

        buttonFire = new TextButton("Fire", fireBSkin);


        buttonAmmo = new TextButton("Ammo", menuSkin);
        arrowLeftSkin = new Skin(Gdx.files.internal("skins/arrowLeft.json"), new TextureAtlas(Gdx.files.internal("skins/leftArrow.pack")));
        arrowLeft = new ImageButton(arrowLeftSkin);
        arrowLeft.setName("arrowLeft");
        arrowRightSkin = new Skin(Gdx.files.internal("skins/arrowRight.json"), new TextureAtlas(Gdx.files.internal("skins/rightArrow.pack")));
        arrowRight = new ImageButton(arrowRightSkin);
        arrowRight.setName("arrowRight");
        windowSkin = new Skin(Gdx.files.internal("skins/windowSkin.json"), new TextureAtlas(Gdx.files.internal("skins/windowPack.pack")));


        buttonExit = new TextButton("Pause", menuSkin);
        buttonExit.setName("Exit");



        font = new BitmapFont(Gdx.files.internal("font/fireBold.fnt"));

        spriteChosenAmmo = currentPlayer.getTeam().getAmmunitionSprite(currentPlayer.getChosenAmmo().getName());
        spriteChosenAmmo.setSize(Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10);
        spriteChosenAmmo.setPosition(Gdx.graphics.getWidth() - spriteChosenAmmo.getWidth(), Gdx.graphics.getHeight() - spriteChosenAmmo.getHeight());

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

        buttonAmmo.addListener(new AmmoChangeController(gameInstance));

        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game, 0));
            }
        });

        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                new Dialog("", windowSkin){
                    {
                        text("Do you really want to quit?");
                        button("Exit", "go");
                        button("", "mute", windowSkin.get("speaker", TextButton.TextButtonStyle.class));
                        button("Cancel", "stay");

                    }
                    @Override
                    protected void result(final Object object){
                        System.out.println(object.toString());
                        if (object.toString() == "go"){
                            battleSong.stop();
                            game.setScreen(new MainMenu(game, 100));
                        }
                        else if (object.toString() == "mute"){
                            if(battleSong.isPlaying()){
                                battleSong.pause();
                            }else{
                                battleSong.play();}
                        }


                    }
                }.show(stage);
            }
        });


        buttonFire.addListener(new FireController(this, gameInstance));

        stage.addListener(new AngleController(gameInstance));

        arrowLeft.addListener(moveCtrl);
        arrowRight.addListener(moveCtrl);

        fireSkin.getFont("font").setScale(1.0f);

        labelRound = new Label(String.format("Round %d out of %d", gameInstance.getCurrentRound(), gameInstance.getNumberOfRounds()), fireSkin);
        labelRound.setFontScale((float)(Gdx.graphics.getHeight())/900);
        labelCurrentPlayer = new Label("Player " + currentPlayer.getPlayerNumber(), fireSkin);
        labelCurrentPlayer.setFontScale((float)(Gdx.graphics.getHeight())/720);
        labelTurn = new Label("Turns left: " + (gameInstance.getNumberOfTurns() - currentPlayer.getTurnsTaken()), fireSkin);
        labelTurn.setFontScale((float)(Gdx.graphics.getHeight())/900);
        //fireSkin.getFont("font").setScale(1f);
        labelChosenAmmo = new Label("Ammo: " + currentPlayer.getChosenAmmo().getName(), fireSkin);
        //labelLeftAmmo = new Label("Ammo left: " + currentPlayer.getInventory().getAmmoLeft(currentPlayer.getChosenAmmo().getName()), fireSkin);
        //labelChosenAmmo = new Label("Ammo: " + currentPlayer.getChosenAmmo().getName(), fireSkin);
        labelLeftAmmo = new Label("Ammo left: " + currentPlayer.getInventory().getAmmoLeft(currentPlayer.getChosenAmmo().getName()), fireSkin);
        labelHealthLeft = new Label("Health: " + (currentPlayer.getVehicle().getHealth() + currentPlayer.getHealthUpgrade()), fireSkin);
        labelFuelLeft = new Label("Fuel: " + (currentPlayer.getVehicle().getFuel() + currentPlayer.getFuelUpgrade()), fireSkin);
        labelPower = new Label("Power: ", fireSkin);

        labelRound.setColor(Color.BLACK);
        labelCurrentPlayer.setColor(Color.BLACK);
        labelTurn.setColor(Color.BLACK);
        labelChosenAmmo.setColor(Color.BLACK);
        labelLeftAmmo.setColor(Color.BLACK);
        labelHealthLeft.setColor(Color.BLACK);
        labelFuelLeft.setColor(Color.BLACK);
        labelPower.setColor(Color.BLACK);

        float padding = Gdx.graphics.getWidth()/200; // about 10 pixels, padding goes (top, left, bottom, right)

        groupTop1.left().top();
        groupTop1.defaults();
        groupTop1.add(labelCurrentPlayer).pad(padding, 20*padding, padding, 0).fillX();
        groupTop1.add(labelRound).pad(padding, 7*padding, padding, 0).fillX();
        groupTop1.add(labelTurn).pad(padding, 7*padding, padding, 0).fillX();
        groupTop1.add(labelChosenAmmo).pad(padding, 9*padding, padding, 0).fillX().row();

        groupTop2.left().top();
        groupTop2.add(labelHealthLeft).pad(3*padding+labelCurrentPlayer.getHeight(), 30*padding, 0 , 0).fillX();
        groupTop2.add(labelFuelLeft).pad(3*padding+labelCurrentPlayer.getHeight(), 8*padding, 0, 0).fillX();
        groupTop2.add(labelPower).pad(3*padding+labelCurrentPlayer.getHeight(), 8*padding, 0, 20*padding).fillX();
        groupTop2.add(labelLeftAmmo).pad(3*padding+labelCurrentPlayer.getHeight(), 15*padding, 0, 0).fillX().row();

        /*groupTopRight.setDebug(true);
        groupTopRight.right().top();
        groupTopRight.add(labelChosenAmmo);*/


        groupTop1.setFillParent(true);
        groupTop2.setFillParent(true);

        groupBottom.bottom();
        groupBottom.add(buttonFire).height(Gdx.graphics.getHeight()/6).width(Gdx.graphics.getHeight()/6);

        groupLeft.bottom().left();
        groupLeft.add(arrowLeft).padLeft(4*padding).padBottom(4*padding).padRight(25).height(Gdx.graphics.getHeight()/8).width(Gdx.graphics.getWidth()/16);
        groupLeft.add(arrowRight).padLeft(4*padding).padBottom(4 * padding).height(Gdx.graphics.getHeight()/8).width(Gdx.graphics.getWidth()/16);

        groupRight.bottom().right();
        groupRight.add(buttonAmmo).padBottom(2*padding).padLeft(4*padding);
        groupRight.add(buttonExit).padBottom(2*padding).padLeft(4*padding);

        groupBottom.setFillParent(true);
        groupRight.setFillParent(true);
        groupLeft.setFillParent(true);

        stage.addActor(groupTop1);
        stage.addActor(groupTop2);
        stage.addActor(groupBottom);
        stage.addActor(groupRight);
        stage.addActor(groupLeft);

        spriteChosenAmmo.setSize(Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10);
        spriteChosenAmmo.setPosition(labelHealthLeft.getWidth() + labelFuelLeft.getWidth() + labelPower.getWidth() + (3*Gdx.graphics.getWidth()/5), Gdx.graphics.getHeight()-spriteChosenAmmo.getHeight());


        battleSong.play();
        battleSong.setLooping(true);

        Gdx.input.setInputProcessor(stage);

    }


    // is called all the time. draws the game with different positions and values for tanks etc.
    @Override
    public void render(float delta) {

        // if the round is finished (either because there is only one player left, or there is no more turns left
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
                    shapeRenderer.rect(p.getVehicle().getHealthPosition().x, p.getVehicle().getHealthPosition().y, 0, 0, 102, ((float)Gdx.graphics.getHeight()) / 40 + 2, 1, 1, p.getVehicle().getRotation() );
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
                    // draw health


                    shapeRenderer.rect(p.getVehicle().getHealthPosition().x, p.getVehicle().getHealthPosition().y, 0, 0, p.getVehicle().getHealth(), ((float)Gdx.graphics.getHeight()) / 40, 1, 1, p.getVehicle().getRotation() );
                    shapeRenderer.end();
                    batch.begin();

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
        spriteChosenAmmo.draw(batch);

        batch.end();


        //Fire power outline box
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(labelHealthLeft.getWidth()+labelFuelLeft.getWidth()+labelPower.getWidth()+(44*Gdx.graphics.getWidth()/200), Gdx.graphics.getHeight() - (labelCurrentPlayer.getHeight() + (6*Gdx.graphics.getWidth()/200)), 202, Gdx.graphics.getHeight()/30 + 2);
        shapeRenderer.end();

        //Fire power fluctuator
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(249.f / 255, 22.f / 255, 39.f / 255, 1);
        shapeRenderer.rect(labelHealthLeft.getWidth()+labelFuelLeft.getWidth()+labelPower.getWidth()+(44*Gdx.graphics.getWidth()/200), Gdx.graphics.getHeight() - (labelCurrentPlayer.getHeight() + (6*Gdx.graphics.getWidth()/200)), currentPlayer.getVehicle().getPower() * 2, Gdx.graphics.getHeight()/30);
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

        spriteChosenAmmo = currentPlayer.getTeam().getAmmunitionSprite(currentPlayer.getChosenAmmo().getName());
        spriteChosenAmmo.setPosition(labelHealthLeft.getWidth() + labelFuelLeft.getWidth() + labelPower.getWidth() + (3*Gdx.graphics.getWidth()/5), Gdx.graphics.getHeight()-spriteChosenAmmo.getHeight());
        spriteChosenAmmo.setSize(Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10);

        labelRound.setText(String.format("Round %d out of %d", gameInstance.getCurrentRound(), gameInstance.getNumberOfRounds()));
        labelCurrentPlayer.setText("Player " + currentPlayer.getPlayerNumber());
        labelChosenAmmo.setText("Ammo: " + currentPlayer.getChosenAmmo().getName());
        labelLeftAmmo.setText("Ammo left: " + currentPlayer.getInventory().getAmmoLeft(currentPlayer.getChosenAmmo().getName()));
        labelHealthLeft.setText("Health: " + (currentPlayer.getVehicle().getHealth() + currentPlayer.getHealthUpgrade()));
        labelFuelLeft.setText("Fuel: " + (currentPlayer.getFuelUpgrade() + currentPlayer.getVehicle().getFuel()));
        labelPower.setText("Power: ");

        if (currentPlayer.getChosenAmmo().getName().equals("YummyGrenade")) {
            labelLeftAmmo.setText("Ammo left: inf");
        }
        else {
            labelLeftAmmo.setText("Ammo left: " + currentPlayer.getInventory().getAmmoLeft(currentPlayer.getChosenAmmo().getName()));
        }

        if (gameInstance.getNumberOfTurns() == -1) {
            labelTurn.setText("Turns left: inf");
        }
        else {
            labelTurn.setText("Turns left: " + (gameInstance.getNumberOfTurns() - currentPlayer.getTurnsTaken()));

        }




    }

    // draws the ground from the set environment
    private void generateGround() {

        polyBatch.begin();

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
        buttonExit.setVisible(false);
        buttonFire.setVisible(false);
        arrowLeft.setVisible(false);
        arrowRight.setVisible(false);
    }

    public void resumeButtons() {
        buttonAmmo.setVisible(true);
        buttonExit.setVisible(true);
        buttonFire.setVisible(true);
        arrowLeft.setVisible(true);
        arrowRight.setVisible(true);
    }

    public boolean outsideFireButton(float touchUpAtX, float touchUpAtY) {
        return touchUpAtY < buttonFire.getY() || touchUpAtY > (buttonFire.getY() + buttonFire.getHeight()) || touchUpAtX < buttonFire.getX() || touchUpAtX > (buttonFire.getX() + buttonFire.getWidth());
    }

    public void changePlayer() {
        this.gameInstance.changePlayer();
    }

    public void changeRound() {
        this.gameInstance.changeRound();
    }

}