package com.mygdx.game.view;

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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.SettingsController;
import com.mygdx.game.model.AudioVisualManagers.TextureManager;

/**
 * Created by annieaa on 11/03/15.
 */
public class SettingsView implements Screen {

    public MyGdxGame game;

    private Stage stage;
    private Table table;
    private Table container;

    private ScrollPane scroll;

    private Skin skin;

    private ImageButton arrowLeftRounds;
    private ImageButton arrowRightRounds;
    private ImageButton arrowLeftTurns;
    private ImageButton arrowRightTurns;
    private ImageButton arrowLeftDifficulty;
    private ImageButton arrowRightDifficulty;

    private Sprite settingsSprite;
    private SpriteBatch batch;

    private Skin arrowLeftSkin;
    private Skin arrowRightSkin;

    private Label labelNumberOfRounds;
    private Label labelNumberOfTurns;
    private Label labelDifficulty;

    private Label txtNumberOfRounds;
    private Label txtNumberOfTurns;
    private Label txtDifficulty;

    public int numberOfRounds;
    public String numberOfTurns;
    public String difficulty;

    private TextButton buttonMainMenu;
    private TextButton buttonNext;


    public SettingsView(MyGdxGame game) {

        this.game = game;
        stage = new Stage();
        table = new Table();
        container = new Table();
        stage.addActor(container);
        container.setFillParent(true);


        //Makes a scroll pane to support scrolling
        scroll = new ScrollPane(table);

        //Buttons
        arrowLeftSkin = new Skin(Gdx.files.internal("skins/arrowLeft.json"), new TextureAtlas(Gdx.files.internal("skins/leftArrow.pack")));
        arrowLeftRounds = new ImageButton(arrowLeftSkin);
        arrowLeftRounds.setName("arrowLeftRounds");
        arrowRightSkin = new Skin(Gdx.files.internal("skins/arrowRight.json"), new TextureAtlas(Gdx.files.internal("skins/rightArrow.pack")));
        arrowRightRounds = new ImageButton(arrowRightSkin);
        arrowRightRounds.setName("arrowRightRounds");

        arrowLeftTurns = new ImageButton(arrowLeftSkin);
        arrowLeftTurns.setName("arrowLeftTurns");
        arrowRightTurns = new ImageButton(arrowRightSkin);
        arrowRightTurns.setName("arrowRightTurns");

        arrowLeftDifficulty = new ImageButton(arrowLeftSkin);
        arrowLeftDifficulty.setName("arrowLeftDifficulty");
        arrowRightDifficulty = new ImageButton(arrowRightSkin);
        arrowRightDifficulty.setName("arrowRightDifficulty");

        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale((float)(Gdx.graphics.getHeight())/2160);

        numberOfRounds = 1;
        numberOfTurns = "Unlimited";
        difficulty = "Medium";

        //Labels
        labelNumberOfRounds = new Label("Number of rounds: ", skin);
        labelNumberOfTurns = new Label("Turns per round: ", skin);
        labelDifficulty = new Label("Difficulty: ", skin);

        txtNumberOfRounds = new Label("" + numberOfRounds, skin);
        txtNumberOfTurns = new Label("" + numberOfTurns, skin);
        txtDifficulty = new Label("" + difficulty, skin);

        buttonMainMenu = new TextButton("Back", skin);
        buttonMainMenu.setName("Back");
        buttonNext = new TextButton("Next", skin);
        buttonNext.setName("Next");

        scroll.layout();
        table.setFillParent(true);
        stage.addActor(container);
        container.add(scroll);

        //Add everything on the screen
        table.row().padTop((int)(Gdx.graphics.getHeight()/3.6));

        table.add(labelNumberOfRounds).padRight((int)(Gdx.graphics.getHeight()/10.8));
        table.add(arrowLeftRounds);
        table.add(txtNumberOfRounds).colspan(2).center();
        table.add(arrowRightRounds);
        table.row().padTop(Gdx.graphics.getHeight()/36);

        table.add(labelNumberOfTurns);
        table.add(arrowLeftTurns);
        table.add(txtNumberOfTurns).colspan(2).center();
        table.add(arrowRightTurns);
        table.row().padTop(Gdx.graphics.getHeight()/36);

        table.add(labelDifficulty);
        table.add(arrowLeftDifficulty);
        table.add(txtDifficulty).colspan(2).center();
        table.add(arrowRightDifficulty);
        table.row().padTop(Gdx.graphics.getHeight()/27);

        table.add(buttonMainMenu).size((int)(Gdx.graphics.getHeight()/2.7), Gdx.graphics.getHeight()/9).padBottom(5);
        table.add(buttonNext).size((int)(Gdx.graphics.getHeight()/2.7), Gdx.graphics.getHeight()/9).padLeft(Gdx.graphics.getHeight()/18).padBottom(Gdx.graphics.getHeight()/216).colspan(3).row();
        settingsSprite = new Sprite(TextureManager.settingsBackground);
        settingsSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
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



        arrowLeftRounds.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (numberOfRounds == 1) {
                    numberOfRounds = 10;
                }
                else if (numberOfRounds > 1){
                    numberOfRounds--;
                }
                txtNumberOfRounds.setText("" + numberOfRounds);
            }
        });


        arrowRightRounds.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (numberOfRounds == 10) {
                    numberOfRounds = 1;
                }
                else if (numberOfRounds < 10){
                    numberOfRounds++;
                }
                txtNumberOfRounds.setText("" + numberOfRounds);
            }
        });


        arrowLeftTurns.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (numberOfTurns.equals("Unlimited")) {
                    numberOfTurns = Integer.toString(20);

                }
                else if (Integer.parseInt(numberOfTurns) == 5){
                    numberOfTurns = "Unlimited";
                }
                else {
                    numberOfTurns = Integer.toString(Integer.parseInt(numberOfTurns) - 5);
                }
                txtNumberOfTurns.setText(numberOfTurns);
            }
        });


        arrowRightTurns.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (numberOfTurns.equals("Unlimited")) {
                    numberOfTurns = Integer.toString(5);

                }
                else if (Integer.parseInt(numberOfTurns) == 20){
                    numberOfTurns = "Unlimited";
                    table.getCell(txtNumberOfTurns).center();
                }
                else {
                    numberOfTurns = Integer.toString(Integer.parseInt(numberOfTurns) + 5);
                }
                txtNumberOfTurns.setText(numberOfTurns);
            }
        });


        arrowLeftDifficulty.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (difficulty.equals("Easy")) {
                    difficulty = "Hard";
                }
                else if (difficulty.equals("Medium")){
                    difficulty = "Easy";
                }
                else if (difficulty.equals("Hard")) {
                    difficulty = "Medium";
                }
                txtDifficulty.setText(difficulty);
            }
        });


        arrowRightDifficulty.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (difficulty.equals("Easy")) {
                    difficulty = "Medium";
                }
                else if (difficulty.equals("Medium")){
                    difficulty = "Hard";
                }
                else if (difficulty.equals("Hard")) {
                    difficulty = "Easy";
                }
                txtDifficulty.setText(difficulty);
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
