package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.SettingsController;
import com.mygdx.game.model.Enums.Team;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.GameSettings;
import com.mygdx.game.model.TextureManager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by annieaa on 11/03/15.
 */
public class SettingsView extends AbstractView implements Screen {

    public MyGdxGame game;

    private Stage stage;
    private Table table;
    private Table container;

    private ScrollPane scroll;

    private Skin skin;

    private ImageButton arrowLeft;
    private ImageButton arrowRight;
    private ImageButton arrowLeftMoves;
    private ImageButton arrowRightMoves;
    private ImageButton arrowLeftTeam;
    private ImageButton arrowRightTeam;
    private ImageButton arrowLeftPlayer;
    private ImageButton arrowRightPlayer;


    private Sprite settingsSprite;
    private SpriteBatch batch;

    private Skin arrowLeftSkin;
    private Skin arrowRightSkin;

    private Label title;
    private Label labelNumberOfPlayers;
    private Label labelNumberOfTurns;
    private Label labelNumberOfRounds;
    private Label labelPlayer;
    private Label labelTeams;

    private Label txtNumberOfPlayers;
    private Label txtNumberOfTurns;
    private Label txtNumberOfRounds;
    private Label txtPlayer;
    private Label txtTeams;

    private Slider sliderTest;

    private Team[] teamNames;
    private int currentTeam;

    public int numberOfPlayers;
    public int numberOfRounds;
    public int numberOfTurns;
    public int currentPlayer;

    //An ArrayList where the index corresponds with the player's number (index 0 = Player 1) and the value is the team name.
    public ArrayList<Team> teams;

    private TextButton buttonMainMenu;
    private TextButton buttonNext;



    public SettingsView(MyGdxGame game) {

        this.game = game;
        //System.out.println(Team.values().length);
        stage = new Stage();
        table = new Table();
        container = new Table();
        //stage.addActor(container);
        container.setFillParent(true);
        //table.setDebug(true);
        //Makes a scroll pane to support scrolling
        scroll = new ScrollPane(table);


        //Buttons
        arrowLeftSkin = new Skin(Gdx.files.internal("skins/arrowLeft.json"), new TextureAtlas(Gdx.files.internal("skins/leftArrow.pack")));
        arrowLeft = new ImageButton(arrowLeftSkin);
        arrowLeft.setName("arrowLeft");
        arrowRightSkin = new Skin(Gdx.files.internal("skins/arrowRight.json"), new TextureAtlas(Gdx.files.internal("skins/rightArrow.pack")));
        arrowRight = new ImageButton(arrowRightSkin);
        arrowRight.setName("arrowRight");

        arrowLeftMoves = new ImageButton(arrowLeftSkin);
        arrowLeftMoves.setName("arrowLeftMoves");
        arrowRightMoves = new ImageButton(arrowRightSkin);
        arrowRightMoves.setName("arrowRightMoves");

        arrowLeftTeam = new ImageButton(arrowLeftSkin);
        arrowLeftTeam.setName("arrowLeftTeam");
        arrowRightTeam = new ImageButton(arrowRightSkin);
        arrowRightTeam.setName("arrowRightTeam");

        arrowLeftPlayer = new ImageButton(arrowLeftSkin);
        arrowLeftPlayer.setName("arrowLeftPlayer");
        arrowRightPlayer = new ImageButton(arrowRightSkin);
        arrowRightPlayer.setName("arrowRightPlayer");


        //Get an array of team names
        teamNames = getTeamNames();
        currentTeam = 0;

        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale((float)0.5);

        numberOfPlayers = 2;
        currentPlayer = 1;

        teams = new ArrayList<Team>();

        //Initiate all players as the same team
        for (int i = 0; i < numberOfPlayers; i++){
            teams.add(teamNames[0]);
        }

        numberOfRounds = 1;
        numberOfTurns = 2;

        //Labels
        title = new Label("Settings", skin);
        title.setFontScale((float)2);
        labelNumberOfPlayers = new Label("Number of players: ", skin);
        labelNumberOfRounds = new Label("Number of rounds: ", skin);
        labelNumberOfTurns = new Label("Turns per round: ", skin);
        labelTeams = new Label("playing as  ", skin);
        labelPlayer = new Label("Player ", skin);

        txtNumberOfPlayers = new Label("" + numberOfPlayers, skin);
        txtNumberOfRounds = new Label("" + numberOfRounds, skin);
        txtNumberOfTurns = new Label("" + numberOfTurns, skin);
        txtTeams = new Label("" + teamNames[0].getName(), skin);
        txtPlayer = new Label("" + currentPlayer, skin);

        buttonMainMenu = new TextButton("Main Menu", skin);
        buttonNext = new TextButton("Next", skin);
        buttonNext.setName("Next");

        /*Skin skinSlider = new Skin();
        skinSlider.add("sliderbackground", Gdx.files.internal("design/slider_background.png"));
        skinSlider.add("sliderknob", Gdx.files.internal("design/slider_knob.png"));


        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = skinSlider.getDrawable("sliderbackground");
        sliderStyle.knob = skinSlider.getDrawable("sliderknob");

        sliderTest = new Slider(0, 10, 1, false, sliderStyle);*/



        scroll.layout();
        //table.setFillParent(true);

        stage.addActor(container);
        container.add(scroll);


        //Add everything on the screen
        //table.add(title).padBottom(20).row();

        table.row().padTop(300);

        //table.add(sliderTest);

        table.add(labelNumberOfRounds);
        table.add(arrowLeft);
        table.add(txtNumberOfRounds).width(100);
        table.add(arrowRight);
        table.row().padTop(30);

        table.add(labelNumberOfTurns);
        table.add(arrowLeftMoves);
        table.add(txtNumberOfTurns).width(100);
        //table.add(txtNumberOfMoves).width(200).padLeft(50);
        table.add(arrowRightMoves);
        table.row().padTop(30);
        table.add(labelPlayer);
        table.add(arrowLeftPlayer);
        table.add(txtPlayer).width(100);
        table.add(arrowRightPlayer);
        table.row().padTop(30);
        table.add(labelTeams);
        table.add(arrowLeftTeam);
        table.add(txtTeams).width(600).padLeft(40);
        table.add(arrowRightTeam);
        table.row().padTop(40);
        table.add(buttonMainMenu).size(400, 120).padBottom(5);
        table.add(buttonNext).size(400, 120).padLeft(60).padBottom(5).colspan(3).row();

        settingsSprite = new Sprite(TextureManager.settingsBackground);
        settingsSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
    }

    //Returns the team names from the Team enum
    private Team[] getTeamNames(){
        Team[] s = new Team[Team.values().length];
        int i  = 0;
        for (Team t : Team.values()){
            s[i] = t;
            i++;

        }
        return s;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        settingsSprite.draw(batch);
        batch.end();
        stage.act();
        stage.draw();

    }


    @Override
    public void show() {

        //Button listeners
        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game, 100));
            }
        });

        buttonNext.addListener(new SettingsController(this));

        /*buttonNext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TeamView(game, new GameSettings()));
            }
        });*/

        arrowLeft.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (numberOfRounds > 1){
                    numberOfRounds--;
                    txtNumberOfRounds.setText("" + numberOfRounds);
                }

            }
        });
        arrowRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                numberOfRounds++;
                txtNumberOfRounds.setText("" + numberOfRounds);

            }
        });

        arrowLeftMoves.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (numberOfTurns > 1){
                    numberOfTurns--;
                    txtNumberOfTurns.setText("" + numberOfTurns);
                }

            }
        });
        arrowRightMoves.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                numberOfTurns++;
                txtNumberOfTurns.setText("" + numberOfTurns);

            }
        });

        arrowLeftTeam.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Check if the current player's team is the first in the teamNames array.
                if (Arrays.asList(teamNames).indexOf(teams.get(currentPlayer-1)) == 0){
                    //set the current player's team to be the last in the teamNames array
                    teams.set(currentPlayer - 1, teamNames[teamNames.length - 1]);
                }
                else{
                    teams.set(currentPlayer - 1, teamNames[Arrays.asList(teamNames).indexOf(teams.get(currentPlayer-1)) - 1]);
                }
                txtTeams.setText(teams.get(currentPlayer - 1).getName());
            }
        });

        arrowRightTeam.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Just the opposite of the corresponding left arrow
                if (Arrays.asList(teamNames).indexOf(teams.get(currentPlayer-1)) == teamNames.length - 1){
                    teams.set(currentPlayer - 1, teamNames[0]);
                }
                else{
                    teams.set(currentPlayer - 1, teamNames[Arrays.asList(teamNames).indexOf(teams.get(currentPlayer-1)) + 1]);
                }
                txtTeams.setText(teams.get(currentPlayer - 1).getName());
            }
        });

        arrowLeftPlayer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentPlayer == 1){
                    currentPlayer = numberOfPlayers;
                }
                else{
                    currentPlayer--;
                }
                txtPlayer.setText("" + currentPlayer);
                txtTeams.setText(teams.get(currentPlayer - 1).getName());

            }
        });
        arrowRightPlayer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentPlayer == numberOfPlayers){
                    currentPlayer = 1;
                }
                else{
                    currentPlayer++;
                }
                txtPlayer.setText("" + currentPlayer);
                txtTeams.setText(teams.get(currentPlayer - 1).getName());
            }
        });


        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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
