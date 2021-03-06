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
import com.mygdx.game.model.AudioVisualManagers.TextureManager;

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

    private Label currentPlayerLabel, moneyLabel, txtCurrentPlayer, txtMoney, placeholderLabel, infoLabel;
    private TextButton back;
    private TextButton buy;
    private TextButton undo;

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

        container.setWidth(Gdx.graphics.getWidth());
        container.setHeight(Gdx.graphics.getHeight() / 10 * 2);
        ammoContainer.setWidth(Gdx.graphics.getWidth());
        ammoContainer.setHeight(Gdx.graphics.getHeight() / 10 * 5);
        bottomContainer.setWidth(Gdx.graphics.getWidth());
        bottomContainer.setHeight(Gdx.graphics.getHeight() / 10 * 3);

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
        skin.getFont("font").scale((float)Gdx.graphics.getHeight()/10800);

        this.back = new TextButton("Next Player", skin);
        this.buy = new TextButton("Buy", skin);
        this.undo = new TextButton("Undo", skin);

        this.buy.setName("Buy");
        this.back.setName("Back");
        this.undo.setName("Undo");
        undo.setVisible(false);

        placeholderLabel = new Label("", skin);
        currentPlayerLabel = new Label("Current Player:", skin);
        moneyLabel = new Label("Available Money: " + "Number of current ammo: ", skin);
        txtAmmo = "" + buyingPlayer.getInventory().getAmmoLeft(shownAmmo);
        txtCurrentPlayer = new Label("Player 1", skin);
        txtCurrentPlayer.setFontScale(2);
        txtMoney = new Label("$" + buyingPlayer.getScore() + "\n " + txtAmmo, skin);
        currentAmmoSprite = getCurrentSprite();
        infoLabel = new Label(Store.getAmmunition(shownAmmo).getInfoText(), skin);

        container.add(placeholderLabel).prefWidth(Gdx.graphics.getWidth());
        container.add(moneyLabel).expand().padTop(Gdx.graphics.getHeight()/10).top().right();
        container.add(txtMoney).top().padTop(Gdx.graphics.getHeight()/10).padRight(Gdx.graphics.getWidth()/10);
        container.row().fillX();

        ammoContainer.add(arrowLeft).left().height(Gdx.graphics.getHeight()/8).width(Gdx.graphics.getWidth()/16).padLeft(Gdx.graphics.getWidth() / 10);
        ammoContainer.add(infoLabel).prefWidth(Gdx.graphics.getWidth()).prefHeight(Gdx.graphics.getHeight() / 10 * 5).padLeft(Gdx.graphics.getWidth()/20*7).right();
        ammoContainer.add(arrowRight).right().height(Gdx.graphics.getHeight()/8).width(Gdx.graphics.getWidth()/16).padRight(Gdx.graphics.getWidth() / 10);

        bottomContainer.row();
        bottomContainer.add(currentPlayerLabel).colspan(3).bottom().padTop((float)(Gdx.graphics.getHeight()) / 10 * 7).padLeft((float)(Gdx.graphics.getHeight())/10);
        bottomContainer.row();
        bottomContainer.add(txtCurrentPlayer).colspan(3).padLeft(Gdx.graphics.getWidth()/15).bottom();

        bottomContainer.add(undo).prefWidth(Gdx.graphics.getWidth()/ 20 * 4).padLeft(Gdx.graphics.getWidth() / 20).padRight(Gdx.graphics.getWidth() / 20);
        bottomContainer.add(buy).prefWidth(Gdx.graphics.getWidth() / 20 * 4);
        bottomContainer.add(back).prefWidth(Gdx.graphics.getWidth()/20 * 4).padLeft(Gdx.graphics.getWidth() / 20).padRight(Gdx.graphics.getWidth() / 20);

        sprite = new Sprite(TextureManager.storeBackground);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        currentAmmoSprite.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/3);
        currentAmmoSprite.setPosition(Gdx.graphics.getWidth()/20 * 4, Gdx.graphics.getHeight()/10 * 5 - currentAmmoSprite.getHeight()/2);


        batch = new SpriteBatch();
    }

    public void show (){

        back.addListener(new StoreController(this, storeModel, players));
        buy.addListener(new StoreController(this, storeModel, players));
        undo.addListener(new StoreController(this, storeModel, players));

        arrowLeft.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ammoForPurchase.indexOf(storeModel.getShownAmmo()) == 0){
                    storeModel.setShownAmmo(ammoForPurchase.get(ammoForPurchase.size() - 1));
                }
                else{
                    storeModel.setShownAmmo(ammoForPurchase.get(ammoForPurchase.indexOf(storeModel.getShownAmmo()) - 1));
                }
                shownAmmo = storeModel.getShownAmmo();
                infoLabel.setText(Store.getAmmunition(shownAmmo).getInfoText());
            }
        });

        arrowRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ammoForPurchase.indexOf(storeModel.getShownAmmo()) == ammoForPurchase.size() - 1){
                    storeModel.setShownAmmo(ammoForPurchase.get(0));
                }
                else{
                    storeModel.setShownAmmo(ammoForPurchase.get(ammoForPurchase.indexOf(storeModel.getShownAmmo()) + 1));
                }
                shownAmmo = storeModel.getShownAmmo();
                infoLabel.setText(Store.getAmmunition(shownAmmo).getInfoText());
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
        currentAmmoSprite.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/3);
        currentAmmoSprite.setPosition(Gdx.graphics.getWidth()/20 * 4, Gdx.graphics.getHeight()/10 * 5 - currentAmmoSprite.getHeight()/2);

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

    public void setBackText(String s) { this.back.setText(s); }

    public void setCurrentPlayerText(String s) { this.txtCurrentPlayer.setText(s);}

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

    public void showUndo() { undo.setVisible(true); }

    public void hideUndo(){
        undo.setVisible(false);
    }

}
