package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.Enums.Team;

/**
 * Created by annieaa on 11/03/15.
 */
public class SettingsView implements Screen {

    MyGdxGame game;

    private Stage stage;
    private Table table;
    private Table container;

    private ScrollPane scroll;

    private Skin skin;

    private ImageButton arrowLeft;
    private ImageButton arrowRight;
    private ImageButton arrowLeftRoundTime;
    private ImageButton arrowRightRoundTime;
    private ImageButton arrowLeftTeam;
    private ImageButton arrowRightTeam;

    private Skin arrowLeftSkin;
    private Skin arrowRightSkin;

    private Label title;
    private Label labelNumberOfPlayers;
    private Label labelRoundTime;
    private Label labelNumberOfRounds;
    private Label labelTeams;

    private Label txtNumberOfPlayers;
    private Label txtRoundTime;
    private Label txtNumberOfRounds;
    private Label txtTeams;

    private String[] teamNames;
    private int currentTeam;

    private int numberOfPlayers;
    private int numberOfRounds;
    private int roundTime;

    private TextButton buttonMainMenu;


    public SettingsView(MyGdxGame game) {

        this.game = game;
        System.out.println(Team.values().length);
        stage = new Stage();
        table = new Table();
        container = new Table();
        //stage.addActor(container);
        container.setFillParent(true);

        scroll = new ScrollPane(table);


        arrowLeftSkin = new Skin(Gdx.files.internal("skins/arrowLeft.json"), new TextureAtlas(Gdx.files.internal("skins/leftArrow.pack")));
        arrowLeft = new ImageButton(arrowLeftSkin);
        arrowLeft.setName("arrowLeft");
        arrowRightSkin = new Skin(Gdx.files.internal("skins/arrowRight.json"), new TextureAtlas(Gdx.files.internal("skins/rightArrow.pack")));
        arrowRight = new ImageButton(arrowRightSkin);
        arrowRight.setName("arrowRight");

        arrowLeftRoundTime = new ImageButton(arrowLeftSkin);
        arrowLeftRoundTime.setName("arrowLeftRoundTime");
        arrowRightRoundTime = new ImageButton(arrowRightSkin);
        arrowRightRoundTime.setName("arrowRightRoundTime");

        arrowLeftTeam = new ImageButton(arrowLeftSkin);
        arrowLeftTeam.setName("arrowLeftTeam");
        arrowRightTeam = new ImageButton(arrowRightSkin);
        arrowRightTeam.setName("arrowRightTeam");

        teamNames = getTeamNames();
        currentTeam = 0;

        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale(1);

        numberOfPlayers = 2;
        numberOfRounds = 1;
        roundTime = 1;

        title = new Label("Settings", skin);
        labelNumberOfPlayers = new Label("Number of players: ", skin);
        labelNumberOfRounds = new Label("Number of rounds: ", skin);
        labelRoundTime = new Label("Round time: ", skin);
        labelTeams = new Label("Select your team: ", skin);

        txtNumberOfPlayers = new Label("" + numberOfPlayers, skin);
        txtNumberOfRounds = new Label("" + numberOfRounds, skin);
        txtRoundTime = new Label("" + roundTime + ":00", skin);
        txtTeams = new Label("" + teamNames[0], skin);

        buttonMainMenu = new TextButton("To Main Menu", skin);



        scroll.layout();
        table.setFillParent(true);
        stage.addActor(container);
        container.add(scroll).fill();

        table.add(title).padBottom(20).row();

        table.add(labelNumberOfRounds).padBottom(20).padRight(40);
        table.add(arrowLeft);
        table.add(txtNumberOfRounds).padRight(40);
        table.add(arrowRight).row();

        table.add(labelRoundTime).padBottom(20).padRight(40);
        table.add(arrowLeftRoundTime);
        table.add(txtRoundTime).padRight(40);
        table.add(arrowRightRoundTime).row();

        table.add(labelTeams).padBottom(20).padRight(40);
        table.add(arrowLeftTeam);
        table.add(txtTeams).padRight(40);
        table.add(arrowRightTeam).row();

        table.add(buttonMainMenu).size(900, 120).padBottom(20).row();
        /*table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(20).row();
        table.add(new Label("LABEL", skin)).padBottom(200).row();*/

    }

    private String[] getTeamNames(){
        String[] s = new String[Team.values().length];
        int i  = 0;
        for (Team t : Team.values()){
            s[i] = t.getName();
            i++;

        }
        return s;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }


    @Override
    public void show() {

        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
            }
        });

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

        arrowLeftRoundTime.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (roundTime > 1){
                    roundTime--;
                    txtRoundTime.setText("" + roundTime + ":00");
                }

            }
        });
        arrowRightRoundTime.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                roundTime++;
                txtRoundTime.setText("" + roundTime + ":00");

            }
        });

        arrowLeftTeam.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentTeam == 0){
                    currentTeam = teamNames.length - 1;
                    txtTeams.setText(teamNames[currentTeam]);
                }
                else{
                    currentTeam--;
                    txtTeams.setText(teamNames[currentTeam]);
                }

            }
        });
        arrowRightTeam.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentTeam == teamNames.length - 1){
                    currentTeam = 0;
                    txtTeams.setText(teamNames[currentTeam]);
                }
                else{
                    currentTeam++;
                    txtTeams.setText(teamNames[currentTeam]);
                }
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
