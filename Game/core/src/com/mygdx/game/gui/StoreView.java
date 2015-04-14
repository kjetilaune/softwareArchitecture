package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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

import java.util.ArrayList;

/**
 * Created by Eplemaskin on 14/04/15.
 */
public class StoreView implements Screen{
    private MyGdxGame game;
    private Game gameInstance;
    private Player currentPlayer;
    private ArrayList<Player> players;
    private int numberOfPlayers;

    //GUI
    private Stage stage;
    private Table container;

    private Skin skin;
    private Label title, currentPlayerLabel, priceLabel, moneyLabel, txtPrice, txtCurrentPlayer, txtMoney;
    private TextButton back;

    public StoreView(MyGdxGame game, Game gameInstance){
        this.game = game;
        this.gameInstance = gameInstance;
        this.players = gameInstance.getPlayers();
        this.currentPlayer = players.get(0);
        numberOfPlayers = players.size();

        //GUI
        stage = new Stage();
        container = new Table();


        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale((float)0.5);

        this.back = new TextButton("Back to game", skin);

        title = new Label("Store", skin);
        currentPlayerLabel = new Label("Current Player: ", skin);
        priceLabel = new Label("Price: ", skin);
        moneyLabel = new Label("Available Money: ", skin);
        txtPrice = new Label("", skin);
        txtCurrentPlayer = new Label("Player 1", skin);
        txtMoney = new Label("$" + currentPlayer.getMoney(), skin);

        container.add(title).padBottom(40);
        container.add(moneyLabel);
        container.add(txtMoney);
        container.row();
        container.add(back);


        stage.addActor(container);
    }

    public void show (){
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game, 100));
            }
        });

        Gdx.input.setInputProcessor(stage);
    }


    public void render (float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }


    public void resize (int width, int height){}


    public void pause (){}


    public void resume (){}


    public void hide (){}


    public void dispose (){}

}
