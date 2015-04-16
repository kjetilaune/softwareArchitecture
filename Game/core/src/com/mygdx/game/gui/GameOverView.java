package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.TextureManager;

/**
 * Created by Jonathan on 12.03.2015.
 */
public class GameOverView extends AbstractView implements Screen {


    private MyGdxGame game;
    private Game gameInstance;

    private Stage stage;
    private Table table;

    private Skin skin;
    private TextButton buttonMainMenu;

    private Sprite titleSprite;
    private SpriteBatch batch;



    public GameOverView(MyGdxGame game, Game gameInstance) {

        this.game = game;
        this.gameInstance = gameInstance;

        stage = new Stage();
        table = new Table();

        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale(1);

        buttonMainMenu = new TextButton("Main Menu", skin);

        titleSprite = new Sprite(TextureManager.titleBackground);
        titleSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();


    }

    //Should display the winning tank and some statistics maybe

    public void render (float delta){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        titleSprite.draw(batch);
        batch.end();

    }

    public void show (){

        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game, 100));
            }
        });

    }

    public void resize (int width, int height){}


    public void pause (){}


    public void resume (){}


    public void hide (){}


    public void dispose (){}
}
