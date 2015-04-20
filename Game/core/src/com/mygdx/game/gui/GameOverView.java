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
    private BitmapFont font;
    private TextButton buttonMainMenu;
    private Label txtWinningPlayer;
    private Label txtWinningTeam;

    private Sprite titleSprite;

    private Player winner;
    private Sprite winnerTank;

    private SpriteBatch batch;



    public GameOverView(MyGdxGame game, Game gameInstance) {

        System.out.println("Height: " + Gdx.graphics.getHeight() + " Width: " + Gdx.graphics.getWidth());

        this.game = game;
        this.gameInstance = gameInstance;

        //winner = gameInstance.getGameWinner();
        winner = gameInstance.getGameWinners().get(0);
        winnerTank = winner.getTeam().getVehicleSprite();
        winnerTank.scale(5);
        winnerTank.setPosition(Gdx.graphics.getWidth()/2 - winnerTank.getWidth()/2, Gdx.graphics.getHeight()/2 - winnerTank.getHeight()/2);

        stage = new Stage();
        tableTop = new Table();
        tableBottom = new Table();

        tableTop.setFillParent(true);
        tableBottom.setFillParent(true);

        stage.addActor(tableTop);
        stage.addActor(tableBottom);

        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale(1);
        font = new BitmapFont(Gdx.files.internal("font/rav.fnt"), Gdx.files.internal("font/rav.png"), false);

        buttonMainMenu = new TextButton("Main Menu", skin);
        //statistics = new Label(String.format("The winner is Player %d with %s", winner.getPlayerNumber(), winner.getTeam().getName()), skin);

        txtWinningPlayer = new Label("The winner is player " + winner.getPlayerNumber(), skin);
        txtWinningTeam = new Label(winner.getTeam().getName(), skin);

        //titleSprite = new Sprite(TextureManager.titleBackground);
        //titleSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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

        tableTop.top();
        tableTop.add(txtWinningPlayer).fillX().padBottom(Gdx.graphics.getHeight()/3);
        tableTop.add(txtWinningTeam).fillX();

        tableBottom.bottom();
        tableBottom.add(buttonMainMenu).fillX().padBottom(Gdx.graphics.getHeight()/10);

        Gdx.input.setInputProcessor(stage);

    }

    //Should display the winning tank and some statistics maybe

    public void render (float delta){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        winnerTank.draw(batch);

        /*font.draw(batch, "The winner is", stage.getWidth()/2, 5*stage.getHeight()/8);
        font.draw(batch, "Player " + winner.getPlayerNumber(), stage.getWidth()/2, 4*stage.getHeight()/8);
        font.draw(batch, "Team " + winner.getTeam().getName(), stage.getWidth()/2, 3*stage.getHeight()/8);*/

        //titleSprite.draw(batch);
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
