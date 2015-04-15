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
import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Store;
import com.mygdx.game.model.TextureManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eplemaskin on 14/04/15.
 */
public class StoreView implements Screen{
    private MyGdxGame game;
    private Game gameInstance;
    private Player currentPlayer;

    private ArrayList<Player> players;
    private ArrayList<Ammunition> ammos;
    private ArrayList<String> ammoNames;

    private HashMap<String, Ammunition> allAmmunition;

    private Ammunition currentAmmo;
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

    private Sprite sprite, currentAmmoSprite;
    private SpriteBatch batch;

    public StoreView(MyGdxGame game, Game gameInstance){
        this.game = game;
        this.gameInstance = gameInstance;
        this.players = gameInstance.getPlayers();
        this.currentPlayer = players.get(0);
        numberOfPlayers = players.size();
        allAmmunition = Store.getInstance().getAllAmmunition();
        for (Map.Entry<String, Ammunition> entry : allAmmunition.entrySet())
        {
            //System.out.println(entry.getKey() + "/" + entry.getValue().getName());
            ammos.add(entry.getValue());
            ammoNames.add(entry.getKey());
        }
        //ammos = currentPlayer.getInventory().getAmmunitions();
        //currentAmmo = currentPlayer.getChosenAmmo();
        currentAmmo = ammos.get(0);

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
        currentAmmoSprite = getCurrentSprite();

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

        ammoContainer.add(arrowLeft).left().maxWidth(stage.getWidth() / 20).padLeft(stage.getWidth() / 10);
        ammoContainer.add(placeholderLabel).prefWidth(stage.getWidth()).prefHeight(stage.getHeight() / 10 * 5);
        ammoContainer.add(arrowRight).right().maxWidth(stage.getWidth() / 20).padRight(stage.getWidth() / 10);

        bottomContainer.row();
        bottomContainer.add(currentPlayerLabel).prefWidth(stage.getWidth()/20 * 7).prefHeight(stage.getHeight()/10 * 1).bottom().padTop(stage.getHeight() / 10 * 7);
        bottomContainer.row();
        bottomContainer.add(txtCurrentPlayer).prefWidth(stage.getWidth()/20 * 7).prefHeight(stage.getHeight()/10 * 3).padLeft(stage.getWidth()/20).bottom();
        bottomContainer.add(buy).prefWidth(stage.getWidth() / 20 * 4);
        bottomContainer.add(back).prefWidth(stage.getWidth()/20 * 8).padLeft(stage.getWidth() / 20).padRight(stage.getWidth() / 20);

        sprite = new Sprite(TextureManager.storeBackground);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        currentAmmoSprite.setPosition(stage.getWidth()/20 * 6, stage.getHeight()/10 * 5 - currentAmmoSprite.getHeight()/2);
        currentAmmoSprite.setScale(10);

        batch = new SpriteBatch();




    }

    public void show (){
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("AMMOSIZE" + ammos.size());
                //game.setScreen(new MainMenu(game, 100));
                if (currentPlayer == players.get(players.size()-2)){
                    currentPlayer = players.get(players.size()-1);
                    currentAmmo = ammos.get(0);
                    txtMoney.setText("" + currentPlayer.getMoney());
                    txtCurrentPlayer.setText("Player " + (players.indexOf(currentPlayer) + 1));
                    back.setText("New round");
                }
                else if (currentPlayer == players.get(players.size()-1)){
                    game.setScreen(new MainMenu(game, 100));
                }
                else{
                    currentPlayer = players.get(players.indexOf(currentPlayer));
                    currentAmmo = ammos.get(0);
                    txtMoney.setText("" + currentPlayer.getMoney());
                    txtCurrentPlayer.setText("Player " + (players.indexOf(currentPlayer) + 1));
                }
            }
        });

        arrowLeft.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("AMMOSIZE" + ammos.size());
                if (ammos.indexOf(currentAmmo) == 0){
                    currentAmmo = ammos.get(ammos.size() - 1);
                }
                else{
                    currentAmmo = ammos.get(ammos.indexOf(currentAmmo) - 1);
                }


            }
        });

        arrowRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("AMMOSIZE" + ammos.size());
                if (ammos.indexOf(currentAmmo) == ammos.size()-1){
                    currentAmmo = ammos.get(0);
                }
                else{
                    currentAmmo = ammos.get(ammos.indexOf(currentAmmo) + 1);
                }

            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private Sprite getCurrentSprite(){
        //System.out.println("Sprite" + currentPlayer.getTeam().getAmmunitionSprite(currentPlayer.getChosenAmmo().getName()));
        return currentPlayer.getTeam().getAmmunitionSprite(currentAmmo.getName());
    }


    public void render (float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentAmmoSprite = getCurrentSprite();
        currentAmmoSprite.setPosition(stage.getWidth()/20 * 6, stage.getHeight()/10 * 5 - currentAmmoSprite.getHeight()/2);
        currentAmmoSprite.setScale(10);

        batch.begin();
        sprite.draw(batch);
        currentAmmoSprite.draw(batch);
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
