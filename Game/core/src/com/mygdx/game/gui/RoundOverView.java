package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
public class RoundOverView extends AbstractView implements Screen{


    public MyGdxGame game;
    private Game gameInstance;
    private GameView gameView;

    private final int winnerScoreAward = 5000;
    private final int loserScoreAward = 2000;

    private Sprite sprite;

    //GUI
    private Stage stage;
    private Table container;
    private Table teamContainer;
    private Table bottomContainer;

    private Skin skin;

    private ArrayList<Label> labelsTeams, labelsPlayers;

    private Label labelCurrentPlayer, labelPlaceholder;
    private TextButton buttonNext;

    private ArrayList<Sprite> teamSprites;
    private SpriteBatch batch;

    //private Player winner;
    private ArrayList<Player> winners;

    private Music winningMusic;


    public RoundOverView(MyGdxGame game, Game gameInstance, GameView gameView){

        this.game = game;
        this.gameInstance = gameInstance;
        this.gameView = gameView;

        this.winners = gameInstance.getRoundWinners();
        teamSprites = new ArrayList<Sprite>();

        for (Player p : winners) {
            teamSprites.add(p.getTeam().getVehicleSprite());
        }

        awardPlayers();


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


        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale((float)0.1);

        this.buttonNext = new TextButton("Continue to Store", skin);
        this.buttonNext.setName("Continue");

        labelPlaceholder = new Label("", skin);

        if (winners.size() == 1) {
            labelCurrentPlayer = new Label("Player " + winners.get(0).getPlayerNumber() + " won the round!", skin);
        }
        else {
            labelCurrentPlayer = new Label("It's a tie!", skin);
        }

        labelCurrentPlayer.setFontScale(2);

        labelsTeams = new ArrayList<Label>();
        //labelsPlayers = new ArrayList<Label>();

        for (int i = 0 ; i < winners.size() ; i++) {
            labelsTeams.add(new Label(winners.get(i).getTeam().getName(), skin));
            labelsTeams.get(i).setFontScale(2);
        }
        teamContainer.add(labelPlaceholder).prefWidth(stage.getWidth()).prefHeight(stage.getHeight() / 10 * 5);


        bottomContainer.row();
        bottomContainer.add(labelCurrentPlayer).prefWidth(stage.getWidth()/20 * 7).prefHeight(stage.getHeight()/10 * 1).bottom().padTop(stage.getHeight() / 10 * 7);
        bottomContainer.row();
        bottomContainer.add(buttonNext).prefWidth(stage.getWidth()/20 * 8).padLeft(stage.getWidth() / 20).padRight(stage.getWidth() / 20);

        sprite = new Sprite(TextureManager.storeBackground);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        System.out.println(" Width: " + Gdx.graphics.getWidth() + " Height: " + Gdx.graphics.getHeight());
        System.out.print("str: " + winners.size());

        if (winners.size() == 1) {
            teamSprites.get(0).setPosition(Gdx.graphics.getWidth()/20 * 6, Gdx.graphics.getHeight()/10 * 5 - teamSprites.get(0).getHeight()/2);
        }
        else {
            teamSprites.get(0).setScale(0.5f);
            teamSprites.get(0).setPosition(Gdx.graphics.getWidth()/20 * 3, Gdx.graphics.getHeight()/10 * 5 - teamSprites.get(0).getHeight()/2);
            teamSprites.get(1).setScale(0.5f);
            teamSprites.get(1).setPosition(Gdx.graphics.getWidth()/20 * 9, Gdx.graphics.getHeight()/10 * 5 - teamSprites.get(1).getHeight()/2);
        }



        batch = new SpriteBatch();

        winningMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/winningMusic.mp3"));

    }

    public void show (){

        buttonNext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                winningMusic.stop();
                game.setScreen(new StoreView(game, gameInstance, gameView));
            }
        });

        winningMusic.play();

        Gdx.input.setInputProcessor(stage);
    }



    public void render (float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        sprite.draw(batch);

        teamSprites.get(0).draw(batch);

        if (winners.size() != 1) {
            teamSprites.get(1).draw(batch);
        }

        batch.end();

        stage.act();
        stage.draw();
    }

    private void awardPlayers(){
        for (Player player : gameInstance.getPlayers()){
            if (winners.contains(player)){
                player.setScore(player.getScore() + winnerScoreAward);
            }
            else{
                player.setScore(player.getScore() + loserScoreAward);
            }
        }
    }


    public void resize (int width, int height){}


    public void pause (){}


    public void resume (){}


    public void hide (){}


    public void dispose (){}


}
