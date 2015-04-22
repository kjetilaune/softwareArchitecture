package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.AudioVisualManagers.TextureManager;

import java.util.ArrayList;

/**
 * Created by Jonathan on 12.03.2015.
 */
public class GameOverView implements Screen {


    private MyGdxGame game;
    private Game gameInstance;

    private Stage stage;
    private Table tableTop;
    private Table tableBottom;

    private Skin skin;

    private ArrayList<Label> labelsTeams;
    private Label labelCurrentPlayer;

    private ArrayList<Sprite> teamSprites;

    private TextButton buttonMainMenu;
    private TextButton buttonRematch;

    private Sprite bg, tieSprite;

    private ArrayList<Player> winners;

    private SpriteBatch batch;

    private Music winningMusic;


    public GameOverView(MyGdxGame game, Game gameInstance) {

        this.game = game;
        this.gameInstance = gameInstance;
        gameInstance.changeRound();

        this.winners = gameInstance.getGameWinners();
        teamSprites = new ArrayList<Sprite>();

        for (Player p : winners) {
            teamSprites.add(p.getTeam().getVehicleSprite());
        }


        //GUI

        stage = new Stage();
        tableTop = new Table();
        tableBottom = new Table();

        tableTop.setFillParent(true);
        tableBottom.setFillParent(true);

        stage.addActor(tableTop);
        stage.addActor(tableBottom);

        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale((float)(Gdx.graphics.getHeight())/1080);

        buttonMainMenu = new TextButton("Main Menu", skin);
        buttonRematch = new TextButton("Rematch", skin);

        if (winners.size() == 1) {
            labelCurrentPlayer = new Label("Player " + winners.get(0).getPlayerNumber() + " won the game!", skin);
        }
        else {
            labelCurrentPlayer = new Label("It's a tie!", skin);
        }

        labelCurrentPlayer.setFontScale(2);


        labelsTeams = new ArrayList<Label>();

        for (int i = 0 ; i < winners.size() ; i++) {
            labelsTeams.add(new Label(winners.get(i).getTeam().getName(), skin));
            labelsTeams.get(i).setFontScale((float)(Gdx.graphics.getHeight())/540);
        }


        tableBottom.row();
        tableBottom.add(labelCurrentPlayer).prefWidth(Gdx.graphics.getWidth()/20 * 7).prefHeight(Gdx.graphics.getHeight()/10 * 1).bottom().padTop(Gdx.graphics.getHeight() / 10 * 7).colspan(2).center();
        tableBottom.row();
        tableBottom.add(buttonMainMenu).prefWidth(Gdx.graphics.getWidth()/20 * 8).padLeft(Gdx.graphics.getWidth() / 20).padRight(Gdx.graphics.getWidth() / 20);
        tableBottom.add(buttonRematch).prefWidth(Gdx.graphics.getWidth()/20 * 8).padLeft(Gdx.graphics.getWidth() / 20).padRight(Gdx.graphics.getWidth() / 20);


        if (winners.size() == 1) {
            teamSprites.get(0).setPosition(Gdx.graphics.getWidth()/20 * 6, Gdx.graphics.getHeight()/10 * 5 - teamSprites.get(0).getHeight()/2);
            teamSprites.get(0).setScale((float)(Gdx.graphics.getHeight())/1080);

        }
        else {
            tieSprite = new Sprite(TextureManager.tie);
            tieSprite.setScale((float)(Gdx.graphics.getHeight())/1080);
            tieSprite.setPosition(Gdx.graphics.getWidth() / 20 * 6, Gdx.graphics.getHeight() / 10 * 5 - teamSprites.get(0).getHeight() / 2);
        }

        batch = new SpriteBatch();

        bg = new Sprite(TextureManager.gameOverBackground);
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        winningMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/winningMusic.mp3"));

    }

    public void show (){

        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                winningMusic.stop();
                game.setScreen(new MainMenu(game, 100));
            }
        });

        buttonRematch.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                winningMusic.stop();
                game.setScreen(new GameView(game, new Game(gameInstance.getSettings())));
            }
        });

        winningMusic.play();

        Gdx.input.setInputProcessor(stage);

    }

    //Should display the winning tank and some statistics maybe

    public void render (float delta){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        bg.draw(batch);
        if (winners.size() == 1) {
            teamSprites.get(0).setScale((float)(Gdx.graphics.getHeight())/1080);
            teamSprites.get(0).draw(batch);
        }
        else{
            tieSprite.setScale((float)(Gdx.graphics.getHeight())/1080);
            tieSprite.draw(batch);
        }

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
