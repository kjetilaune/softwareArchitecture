package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.SettingsController;
import com.mygdx.game.controller.StoreController;
import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.Enums.Team;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.GameSettings;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Store;
import com.mygdx.game.model.TextureManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by annieaa on 16/04/15.
 */
public class TeamView extends AbstractView implements Screen{


    public MyGdxGame game;
    public GameSettings settings;


    public Team currentTeam;
    public Team[] teams;

    //An ArrayList where the index corresponds with the player's number (index 0 = Player 1) and the value is the team name.
    public ArrayList<Team> teamsChosen;

    public int currentTeamNumber;
    public int currentPlayerNumber;
    public int numberOfPlayers;

    //GUI
    private Stage stage;
    private Table container;
    private Table teamContainer;
    private Table bottomContainer;

    private Skin skin;
    private Skin arrowLeftSkin;
    private Skin arrowRightSkin;

    private Label title, labelCurrentPlayer, labelCurrentTeam, labelPlaceholder;
    private TextButton buttonNext;
    private ImageButton arrowLeft, arrowRight;

    private Sprite settingsSprite, currentTeamSprite;
    private SpriteBatch batch;

    private SettingsController settingsController;

    public TeamView(MyGdxGame game, SettingsController settingsController){
        this.game = game;
        this.settingsController = settingsController;
        this.settingsController.setTeamView(this);

        numberOfPlayers = settingsController.settings.getNofPlayers();
        currentPlayerNumber = 0;
        currentTeamNumber = 0;

        teams = Team.values();
        currentTeam = teams[currentTeamNumber];
        teamsChosen = new ArrayList<Team>();


        //GUI
        stage = new Stage();
        container = new Table();
        teamContainer = new Table();
        bottomContainer = new Table();

        container.setFillParent(true);
        teamContainer.setFillParent(true);
        bottomContainer.setFillParent(true);

        stage.addActor(container);
        stage.addActor(teamContainer);
        stage.addActor(bottomContainer);

        container.setWidth(Gdx.graphics.getWidth());
        container.setHeight(Gdx.graphics.getHeight()/10 * 2);
        teamContainer.setWidth(Gdx.graphics.getWidth());
        teamContainer.setHeight(Gdx.graphics.getHeight()/10 * 5);
        bottomContainer.setWidth(Gdx.graphics.getWidth());
        bottomContainer.setHeight(Gdx.graphics.getHeight()/10 * 3);

        arrowLeftSkin = new Skin(Gdx.files.internal("skins/arrowLeft.json"), new TextureAtlas(Gdx.files.internal("skins/leftArrow.pack")));
        arrowLeft = new ImageButton(arrowLeftSkin);
        arrowLeft.setName("arrowLeft");
        arrowRightSkin = new Skin(Gdx.files.internal("skins/arrowRight.json"), new TextureAtlas(Gdx.files.internal("skins/rightArrow.pack")));
        arrowRight = new ImageButton(arrowRightSkin);
        arrowRight.setName("arrowRight");

        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale((float)0.1);

        this.buttonNext = new TextButton("Next Player", skin);
        this.buttonNext.setName("NewGame");

        labelPlaceholder = new Label("", skin);
        labelCurrentPlayer = new Label("Player " + (currentPlayerNumber+1), skin);
        labelCurrentPlayer.setFontScale(2);
        labelCurrentTeam = new Label(currentTeam.getName(), skin);
        labelCurrentTeam.setFontScale(2);

        currentTeamSprite = currentTeam.getVehicleSprite();


        teamContainer.add(arrowLeft).left().maxWidth(stage.getWidth() / 20).padLeft(stage.getWidth() / 10);
        teamContainer.add(labelPlaceholder).prefWidth(stage.getWidth()).prefHeight(stage.getHeight() / 10 * 5);
        teamContainer.add(arrowRight).right().maxWidth(stage.getWidth() / 20).padRight(stage.getWidth() / 10);

        bottomContainer.row();
        bottomContainer.add(labelCurrentPlayer).prefWidth(stage.getWidth()/20 * 7).prefHeight(stage.getHeight()/10 * 1).bottom().padTop(stage.getHeight() / 10 * 7);
        bottomContainer.row();
        bottomContainer.add(buttonNext).prefWidth(stage.getWidth()/20 * 8).padLeft(stage.getWidth() / 20).padRight(stage.getWidth() / 20);

        currentTeamSprite.setPosition(stage.getWidth()/20 * 6, stage.getHeight()/10 * 5 - currentTeamSprite.getHeight()/2);
        //currentTeamSprite.setScale(5);

        settingsSprite = new Sprite(TextureManager.settingsBackground);
        settingsSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();



    }

    public void show (){

        /*buttonNext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentPlayerNumber ++;
            }

        });*/

        buttonNext.addListener(settingsController);

        //back.addListener(new StoreController(this, gameView));


        arrowLeft.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentTeamNumber == 0){
                    currentTeamNumber = teams.length-1;
                }
                else{
                    currentTeamNumber -= 1;
                }
                currentTeam = teams[currentTeamNumber];

            }
        });


        arrowRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(currentTeamNumber == teams.length-1) {
                    currentTeamNumber = 0;
                }
                else {
                    currentTeamNumber += 1;
                }
                currentTeam = teams[currentTeamNumber];

            }
        });


        Gdx.input.setInputProcessor(stage);
    }



    public void render (float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentTeamSprite = currentTeam.getVehicleSprite();

        currentTeamSprite.setPosition(Gdx.graphics.getWidth()/2 - currentTeamSprite.getWidth()/2, Gdx.graphics.getHeight()/2 - currentTeamSprite.getHeight()/2);
        //currentTeamSprite.setScale(5);

        labelCurrentTeam.setText(currentTeam.getName());
        labelCurrentPlayer.setText("Player " + (currentPlayerNumber+1));

        batch.begin();
        settingsSprite.draw(batch);
        currentTeamSprite.draw(batch);
        batch.end();

        stage.act();
        stage.draw();
    }


    public void resize (int width, int height){}


    public void pause (){}


    public void resume (){}


    public void hide (){}


    public void dispose (){}


}
