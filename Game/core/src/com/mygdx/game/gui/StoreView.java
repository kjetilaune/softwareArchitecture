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
import com.mygdx.game.controller.StoreController;
import com.mygdx.game.model.Ammunition;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.Store;
import com.mygdx.game.model.TextureManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eplemaskin on 14/04/15.
 */
public class StoreView extends AbstractView implements Screen{

    public MyGdxGame game;
    public Game gameInstance;
    public GameView gameView;
    public Player currentPlayer;//, roundWinner;
    //private final int winnerScoreAward = 5000;
    //private final int loserScoreAward = 2000;


    public ArrayList<Player> players;
    public ArrayList<Ammunition> ammos;
    private ArrayList<String> ammoNames;

    public ArrayList<String> ammoForPurchase;

    //private HashMap<String, Ammunition> allAmmunition;

    public String currentAmmo, txtAmmo;
    private int numberOfPlayers;

    //GUI
    private Stage stage;
    private Table container;
    private Table ammoContainer;
    private Table bottomContainer;

    private Skin skin;
    private Skin arrowLeftSkin;
    private Skin arrowRightSkin;


    public Label title, currentPlayerLabel, priceLabel, moneyLabel, txtPrice, txtCurrentPlayer, txtMoney, placeholderLabel, infoLabel;
    public TextButton back;
    private TextButton buy;
    private ImageButton arrowLeft, arrowRight;

    private Sprite sprite, currentAmmoSprite;
    private SpriteBatch batch;

    public StoreView(MyGdxGame game, Game gameInstance, GameView gameView){
        this.game = game;
        this.gameView = gameView;
        this.gameInstance = gameInstance;
        this.players = gameInstance.getPlayers();
        this.currentPlayer = players.get(0);
        //this.roundWinner = roundWinner;
        numberOfPlayers = players.size();

        ammoForPurchase = new ArrayList<String>();
        ammoForPurchase.addAll(Store.getInstance().getAmmunitionPrices().keySet());
        Collections.reverse(ammoForPurchase);

        /*
        ammos = new ArrayList<Ammunition>();
        ammoNames = new ArrayList<String>();

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
        */

        currentAmmo = ammoForPurchase.get(0);

        //awardPlayers();
        
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

        //System.out.println("" + stage.getHeight() + ", " + stage.getWidth());
        container.setWidth(stage.getWidth());
        container.setHeight(stage.getHeight()/10 * 2);
        ammoContainer.setWidth(stage.getWidth());
        ammoContainer.setHeight(stage.getHeight()/10 * 5);
        bottomContainer.setWidth(stage.getWidth());
        bottomContainer.setHeight(stage.getHeight()/10 * 3);

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

        this.buy.setName("Buy");
        this.back.setName("Back");

        placeholderLabel = new Label("", skin);
        currentPlayerLabel = new Label("Current Player:", skin);
        priceLabel = new Label("Price: ", skin);
        moneyLabel = new Label("Available Money: " + "Number of current ammo: ", skin);
        txtAmmo = "" + currentPlayer.getInventory().getAmmoLeft(currentAmmo);
        txtPrice = new Label("", skin);
        txtCurrentPlayer = new Label("Player 1", skin);
        txtCurrentPlayer.setFontScale(2);
        txtMoney = new Label("$" + currentPlayer.getScore() + "\n " + txtAmmo, skin);
        currentAmmoSprite = getCurrentSprite();
        infoLabel = new Label("Name of Ammo: " + currentAmmo + "\nPrice: " + Store.getAmmunitionPrice(currentAmmo) + "\nDamage: " + Store.getAmmunition(currentAmmo).getInitialDamage(), skin);


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

        bottomContainer.add(buy).prefWidth(stage.getWidth() / 20 * 4);
        bottomContainer.add(back).prefWidth(stage.getWidth()/20 * 8).padLeft(stage.getWidth() / 20).padRight(stage.getWidth() / 20);


        sprite = new Sprite(TextureManager.storeBackground);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        currentAmmoSprite.setPosition(stage.getWidth()/20 * 4, stage.getHeight()/10 * 5 - currentAmmoSprite.getHeight()/2);
        //currentAmmoSprite.setScale(10);

        batch = new SpriteBatch();




    }

    public void show (){

        back.addListener(new StoreController(this, gameView));
        buy.addListener(new StoreController(this, gameView));

        arrowLeft.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ammoForPurchase.indexOf(currentAmmo) == 0){
                    currentAmmo = ammoForPurchase.get(ammoForPurchase.size() - 1);
                }
                else{
                    currentAmmo = ammoForPurchase.get(ammoForPurchase.indexOf(currentAmmo) - 1);
                }
                infoLabel.setText("Name of Ammo: " + currentAmmo + "\nPrice: " + Store.getAmmunitionPrice(currentAmmo) + "\nDamage: " + Store.getAmmunition(currentAmmo).getInitialDamage());
            }
        });

        arrowRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ammoForPurchase.indexOf(currentAmmo) == ammoForPurchase.size()-1){
                    currentAmmo = ammoForPurchase.get(0);
                }
                else{
                    currentAmmo = ammoForPurchase.get(ammoForPurchase.indexOf(currentAmmo) + 1);
                }
                infoLabel.setText("Name of Ammo: " + currentAmmo + "\nPrice: " + Store.getAmmunitionPrice(currentAmmo) + "\nDamage: " + Store.getAmmunition(currentAmmo).getInitialDamage());
            }
        });


        Gdx.input.setInputProcessor(stage);
    }

    private Sprite getCurrentSprite(){
        //System.out.println("Sprite" + currentPlayer.getTeam().getAmmunitionSprite(currentPlayer.getChosenAmmo().getName()));
        return currentPlayer.getTeam().getAmmunitionSprite(currentAmmo);
    }


    public void render (float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentAmmoSprite = getCurrentSprite();
        currentAmmoSprite.setPosition(stage.getWidth()/20 * 4, stage.getHeight()/10 * 5 - currentAmmoSprite.getHeight()/2);
        //currentAmmoSprite.setScale(10);


        txtAmmo = ("" + currentPlayer.getInventory().getAmmoLeft(currentAmmo));
        this.moneyLabel.setText("Available Money: \n" + "Number of current ammo: ");
        setMoneyText("$" + currentPlayer.getScore() + "\n " + getNumberOfCurrentAmmo());

        batch.begin();
        sprite.draw(batch);
        currentAmmoSprite.draw(batch);
        batch.end();

        stage.act();
        stage.draw();
    }

    /*private void awardPlayers(){
        for (Player player : players){
            if (player.getPlayerNumber() == roundWinner.getPlayerNumber()){
                player.setScore(player.getScore() + winnerScoreAward);
            }
            else{
                player.setScore(player.getScore() + loserScoreAward);
            }
        }
    }*/

    public void setMoneyText(String s){
        this.txtMoney.setText(s);
    }

    public String getNumberOfCurrentAmmo(){
        return "" + currentPlayer.getInventory().getAmmoLeft(currentAmmo);
    }

    public void resize (int width, int height){}


    public void pause (){}


    public void resume (){}


    public void hide (){}


    public void dispose (){}

    public String getCurrentAmmo() {
        return currentAmmo;
    }

}
