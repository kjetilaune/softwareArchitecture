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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.StoreController;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Store;
import com.mygdx.game.model.audioVisualManagers.TextureManager;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Eplemaskin on 14/04/15.
 */
public class StoreView implements Screen{

    private MyGdxGame game;
    private Game gameInstance;
    private GameView gameView;
    private Store storeModel;
    private Player buyingPlayer;

    private ArrayList<Player> players;

    private ArrayList<String> ammoForPurchase;

    private String shownAmmo, txtAmmo;

    //GUI
    private Stage stage;
    private Table container;
    private Table ammoContainer;
    private Table bottomContainer;

    private Skin skin;
    private Skin arrowLeftSkin;
    private Skin arrowRightSkin;

    public Label currentPlayerLabel, moneyLabel, priceLabel, txtPrice, txtCurrentPlayer, txtMoney, placeholderLabel, infoLabel;
    public TextButton back;
    public TextButton buy;
    public TextButton undo;

    private ImageButton arrowLeft, arrowRight;

    private Sprite sprite, currentAmmoSprite;
    private SpriteBatch batch;

    public StoreView(MyGdxGame game, Game gameInstance, GameView gameView){
        this.game = game;
        this.gameView = gameView;
        this.gameInstance = gameInstance;
        this.storeModel = gameInstance.getStore();
        this.players = gameInstance.getPlayers();
        this.buyingPlayer = storeModel.getBuyingPlayer();
        this.shownAmmo = storeModel.getShownAmmo();

        ammoForPurchase = new ArrayList<String>();
        ammoForPurchase.addAll(storeModel.getAmmunitionPrices().keySet());
        Collections.reverse(ammoForPurchase);
        
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

        container.setWidth(stage.getWidth());
        container.setHeight(stage.getHeight() / 10 * 2);
        ammoContainer.setWidth(stage.getWidth());
        ammoContainer.setHeight(stage.getHeight() / 10 * 5);
        bottomContainer.setWidth(stage.getWidth());
        bottomContainer.setHeight(stage.getHeight() / 10 * 3);

        container.setDebug(false);
        ammoContainer.setDebug(false);
        bottomContainer.setDebug(false);

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
        this.undo = new TextButton("Undo", skin);

        this.buy.setName("Buy");
        this.back.setName("Back");
        this.undo.setName("Undo");
        undo.setVisible(false);

        placeholderLabel = new Label("", skin);
        currentPlayerLabel = new Label("Current Player:", skin);
        priceLabel = new Label("Price: ", skin);
        moneyLabel = new Label("Available Money: " + "Number of current ammo: ", skin);
        txtAmmo = "" + buyingPlayer.getInventory().getAmmoLeft(shownAmmo);
        txtPrice = new Label("", skin);
        txtCurrentPlayer = new Label("Player 1", skin);
        txtCurrentPlayer.setFontScale(2);
        txtMoney = new Label("$" + buyingPlayer.getScore() + "\n " + txtAmmo, skin);
        currentAmmoSprite = getCurrentSprite();
        infoLabel = new Label("Name of Ammo: " + shownAmmo + "\nPrice: " + storeModel.getAmmunitionPrice(shownAmmo) + "\nDamage: " + storeModel.getAmmunition(shownAmmo).getInitialDamage(), skin);


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
        ammoContainer.add(infoLabel).prefWidth(stage.getWidth()).prefHeight(stage.getHeight() / 10 * 5).padLeft(stage.getWidth()/20*7).right();
        ammoContainer.add(arrowRight).right().maxWidth(stage.getWidth() / 20).padRight(stage.getWidth() / 10);

        bottomContainer.row();
        bottomContainer.add(currentPlayerLabel).prefWidth(stage.getWidth()/20 * 7).prefHeight(stage.getHeight() / 10 * 1).bottom().padTop(stage.getHeight() / 10 * 7);
        bottomContainer.row();
        bottomContainer.add(txtCurrentPlayer).prefWidth(stage.getWidth()/20 * 7).prefHeight(stage.getHeight()/10 * 3).padLeft(stage.getWidth()/20).bottom();

        bottomContainer.add(undo).prefWidth(stage.getWidth()/ 20*4);
        bottomContainer.add(buy).prefWidth(stage.getWidth() / 20 * 4);
        bottomContainer.add(back).prefWidth(stage.getWidth()/20 * 4).padLeft(stage.getWidth() / 20).padRight(stage.getWidth() / 20);

        sprite = new Sprite(TextureManager.storeBackground);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        currentAmmoSprite.setPosition(stage.getWidth()/20 * 4, stage.getHeight()/10 * 5 - currentAmmoSprite.getHeight()/2);

        batch = new SpriteBatch();
    }

    public void show (){

        back.addListener(new StoreController(this, storeModel, players));
        buy.addListener(new StoreController(this, storeModel, players));

        buy.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent event, float x, float y){
                undo.setVisible(true);
           }
        });

        undo.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //removeAmmo
            }
        });

        arrowLeft.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ammoForPurchase.indexOf(shownAmmo) == 0){
                    shownAmmo = ammoForPurchase.get(ammoForPurchase.size() - 1);
                }
                else{
                    shownAmmo = ammoForPurchase.get(ammoForPurchase.indexOf(shownAmmo) - 1);
                }
                infoLabel.setText("Name of Ammo: " + shownAmmo + "\nPrice: " + storeModel.getAmmunitionPrice(shownAmmo) + "\nDamage: " + storeModel.getAmmunition(shownAmmo).getInitialDamage());
            }
        });

        arrowRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ammoForPurchase.indexOf(shownAmmo) == ammoForPurchase.size()-1){
                    shownAmmo = ammoForPurchase.get(0);
                }
                else{
                    shownAmmo = ammoForPurchase.get(ammoForPurchase.indexOf(shownAmmo) + 1);
                }
                infoLabel.setText("Name of Ammo: " + shownAmmo + "\nPrice: " + storeModel.getAmmunitionPrice(shownAmmo) + "\nDamage: " + storeModel.getAmmunition(shownAmmo).getInitialDamage());
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private Sprite getCurrentSprite(){
        return buyingPlayer.getTeam().getAmmunitionSprite(shownAmmo);
    }


    public void render (float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        buyingPlayer = storeModel.getBuyingPlayer();
        shownAmmo = storeModel.getShownAmmo();

        currentAmmoSprite = getCurrentSprite();
        currentAmmoSprite.setPosition(stage.getWidth()/20 * 4, stage.getHeight()/10 * 5 - currentAmmoSprite.getHeight()/2);

        txtAmmo = ("" + buyingPlayer.getInventory().getAmmoLeft(shownAmmo));
        this.moneyLabel.setText("Available Money: \n" + "Number of current ammo: ");
        setMoneyText("$" + buyingPlayer.getScore() + "\n " + storeModel.getNumberOfCurrentAmmo());

        batch.begin();
        sprite.draw(batch);
        currentAmmoSprite.draw(batch);
        batch.end();

        stage.act();
        stage.draw();
    }

    public void setMoneyText(String s){
        this.txtMoney.setText(s);
    }

    public void resize (int width, int height){}


    public void pause (){}


    public void resume (){}


    public void hide (){}


    public void dispose (){}

    public void newRound() {
        gameView.changeRound();
        gameView.dispose();
        game.setScreen(new GameView(game, gameInstance));
    }

    public String getShownAmmo() {
        return shownAmmo;
    }

    public Player getBuyingPlayer() { return buyingPlayer; }

    public String getInitialAmmo() { return ammoForPurchase.get(0); }

    public void setBuyingPlayer(Player buyingPlayer) {
        this.buyingPlayer = buyingPlayer;
    }

    public void setShownAmmo(String shownAmmo) {
        this.shownAmmo = shownAmmo;
    }

}
