package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
    private Table ammoContainer;
    private Table bottomContainer;

    private Skin skin;
    private Skin arrowLeftSkin;
    private Skin arrowRightSkin;

    private Label title, currentPlayerLabel, priceLabel, moneyLabel, txtPrice, txtCurrentPlayer, txtMoney, placeholderLabel;
    private TextButton back;
    private TextButton buy;
    private ImageButton arrowLeft, arrowRight;

    public StoreView(MyGdxGame game, Game gameInstance){
        this.game = game;
        this.gameInstance = gameInstance;
        this.players = gameInstance.getPlayers();
        this.currentPlayer = players.get(0);
        numberOfPlayers = players.size();

        //GUI
        stage = new Stage();
        container = new Table();
        ammoContainer = new Table();
        bottomContainer = new Table();

        container.setFillParent(true);
        ammoContainer.setFillParent(true);
        bottomContainer.setFillParent(true);

        stage.addActor(container);
        stage.addActor(ammoContainer);
        stage.addActor(bottomContainer);

        System.out.println("" + stage.getHeight() + ", " + stage.getWidth());
        container.setWidth(stage.getWidth());
        container.setHeight(stage.getHeight()/10 * 2);
        ammoContainer.setWidth(stage.getWidth());
        ammoContainer.setHeight(stage.getHeight()/10 * 5);
        bottomContainer.setWidth(stage.getWidth());
        bottomContainer.setHeight(stage.getHeight()/10 * 3);

        container.setDebug(false);
        ammoContainer.setDebug(false);
        bottomContainer.setDebug(true);

        arrowLeftSkin = new Skin(Gdx.files.internal("skins/arrowLeft.json"), new TextureAtlas(Gdx.files.internal("skins/leftArrow.pack")));
        arrowLeft = new ImageButton(arrowLeftSkin);
        arrowLeft.setName("arrowLeft");
        arrowRightSkin = new Skin(Gdx.files.internal("skins/arrowRight.json"), new TextureAtlas(Gdx.files.internal("skins/rightArrow.pack")));
        arrowRight = new ImageButton(arrowRightSkin);
        arrowRight.setName("arrowRight");

        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale((float)0.1);

        this.back = new TextButton("Next Player", skin);
        this.buy = new TextButton("Buy", skin);

        placeholderLabel = new Label("", skin);
        currentPlayerLabel = new Label("Current Player:", skin);
        priceLabel = new Label("Price: ", skin);
        moneyLabel = new Label("Available Money: ", skin);
        txtPrice = new Label("", skin);
        txtCurrentPlayer = new Label("Player 1", skin);
        txtCurrentPlayer.setFontScale(2);
        txtMoney = new Label("$" + currentPlayer.getMoney(), skin);

        /*
        container.add(placeholderLabel).prefHeight(stage.getHeight()/10 * 2).prefWidth(stage.getWidth());
        container.add(moneyLabel).expand().padTop(stage.getHeight()/10).top().right();
        container.add(txtMoney).top().padTop(stage.getHeight()/10);
        container.row().fillX();
        ammoContainer.add(arrowLeft).prefHeight(stage.getHeight()/10 * 5).left().maxWidth(stage.getWidth()/20).padLeft(stage.getWidth()/10).padTop(stage.getHeight()/5);
        ammoContainer.add(placeholderLabel).prefWidth(stage.getWidth());
        ammoContainer.add(arrowRight).right().maxWidth(stage.getWidth()/20).padRight(stage.getWidth()/10).padTop(stage.getHeight()/5);
        ammoContainer.row();
        bottomContainer.add(back).prefHeight(stage.getHeight()/10 * 3).bottom();
        */

        container.add(placeholderLabel).prefWidth(stage.getWidth());
        container.add(moneyLabel).expand().padTop(stage.getHeight()/10).top().right();
        container.add(txtMoney).top().padTop(stage.getHeight()/10).padRight(stage.getWidth()/10);
        container.row().fillX();

        ammoContainer.add(arrowLeft).left().maxWidth(stage.getWidth()/20).padLeft(stage.getWidth()/10);
        ammoContainer.add(placeholderLabel).prefWidth(stage.getWidth()).prefHeight(stage.getHeight()/10 * 5);
        ammoContainer.add(arrowRight).right().maxWidth(stage.getWidth()/20).padRight(stage.getWidth()/10);

        bottomContainer.row();
        bottomContainer.add(currentPlayerLabel).prefWidth(stage.getWidth()/20 * 7).prefHeight(stage.getHeight()/10 * 1).bottom().padTop(stage.getHeight()/10 * 7);
        bottomContainer.row();
        bottomContainer.add(txtCurrentPlayer).prefWidth(stage.getWidth()/20 * 7).prefHeight(stage.getHeight()/10 * 3).padLeft(stage.getWidth()/20).bottom();
        bottomContainer.add(buy).prefWidth(stage.getWidth()/20 * 4);
        bottomContainer.add(back).prefWidth(stage.getWidth()/20 * 8).padLeft(stage.getWidth()/20).padRight(stage.getWidth()/20);


    }

    public void show (){
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new MainMenu(game, 100));
                if (currentPlayer == players.get(players.size()-2)){
                    currentPlayer = players.get(players.size()-1);
                    txtCurrentPlayer.setText("Player " + (players.indexOf(currentPlayer) + 1));
                    back.setText("New round");
                }
                else if (currentPlayer == players.get(players.size()-1)){
                    game.setScreen(new MainMenu(game, 100));
                }
                else{
                    currentPlayer = players.get(players.indexOf(currentPlayer));
                    txtCurrentPlayer.setText("Player " + (players.indexOf(currentPlayer) + 1));
                }
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
