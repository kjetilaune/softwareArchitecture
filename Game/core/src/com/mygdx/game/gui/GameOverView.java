package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.mygdx.game.model.TextureManager;

import java.util.ArrayList;

/**
 * Created by Jonathan on 12.03.2015.
 */
public class GameOverView extends AbstractView implements Screen {


    private MyGdxGame game;
    private Game gameInstance;

    private Stage stage;
    private Table tableTop;
    private Table tableBottom;

    private Skin skin;

    private ArrayList<Label> labelsTeams, labelsPlayers;
    private Label labelCurrentPlayer, labelPlaceholder;

    private ArrayList<Sprite> teamSprites;

    private TextButton buttonMainMenu;

    private Sprite titleSprite;

    private ArrayList<Player> winners;

    private SpriteBatch batch;



    public GameOverView(MyGdxGame game, Game gameInstance) {

        this.game = game;
        this.gameInstance = gameInstance;

        this.winners = gameInstance.getGameWinners();
        //System.out.println("winners: " + gameInstance.getRoundWinners());
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
        skin.getFont("font").scale(1);

        buttonMainMenu = new TextButton("Main Menu", skin);

        if (winners.size() == 1) {
            labelCurrentPlayer = new Label("Player " + winners.get(0).getPlayerNumber() + " won the round!", skin);
        }
        else {
            labelCurrentPlayer = new Label("It's a tie!", skin);
        }

        labelCurrentPlayer.setFontScale(2);

        titleSprite = new Sprite(TextureManager.titleBackground);
        titleSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        labelsTeams = new ArrayList<Label>();

        for (int i = 0 ; i < winners.size() ; i++) {
            labelsTeams.add(new Label(winners.get(i).getTeam().getName(), skin));
            labelsTeams.get(i).setFontScale(2);
        }


        tableBottom.row();
        tableBottom.add(labelCurrentPlayer).prefWidth(stage.getWidth()/20 * 7).prefHeight(stage.getHeight()/10 * 1).bottom().padTop(stage.getHeight() / 10 * 7);
        tableBottom.row();
        tableBottom.add(buttonMainMenu).prefWidth(stage.getWidth()/20 * 8).padLeft(stage.getWidth() / 20).padRight(stage.getWidth() / 20);


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

        //table.add(statistics).top().fillX();



    }

    public void show (){

        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game, 100));
            }
        });
/*
        tableTop.top();
        tableTop.add(txtWinningPlayer).fillX().padBottom(Gdx.graphics.getHeight()/3);
        tableTop.add(txtWinningTeam).fillX();

        tableBottom.bottom();
        tableBottom.add(buttonMainMenu).fillX().padBottom(Gdx.graphics.getHeight()/10);
*/
        Gdx.input.setInputProcessor(stage);

    }

    //Should display the winning tank and some statistics maybe

    public void render (float delta){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        teamSprites.get(0).draw(batch);

        if (winners.size() != 1) {
            teamSprites.get(1).draw(batch);
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
