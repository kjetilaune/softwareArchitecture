package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
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
import com.mygdx.game.model.audioVisualManagers.TextureManager;

import java.util.ArrayList;

/**
 * Created by Eplemaskin on 16/04/15.
 */
public class CreditsView implements Screen{


    public MyGdxGame game;
    private Game gameInstance;
    private GameView gameView;

    private Sprite sprite;

    //GUI
    private Stage stage;
    private Table bottomContainer;
    private SpriteBatch batch;
    private Skin skin;

    private Label creditLabel, placeholderLabel;

    private TextButton buttonBack;




    public CreditsView(MyGdxGame game, Game gameInstance, GameView gameView){




        //GUI
        stage = new Stage();
        bottomContainer = new Table();

        bottomContainer.setFillParent(true);

        stage.addActor(bottomContainer);

        creditLabel = new Label("", skin);
        placeholderLabel = new Label("", skin);

        bottomContainer.setWidth(Gdx.graphics.getWidth());
        bottomContainer.setHeight(Gdx.graphics.getHeight()/10 * 3);


        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").scale((float)0.1);

        this.buttonBack = new TextButton("Back", skin);
        this.buttonBack.setName("Back");


        bottomContainer.add(creditLabel).padTop(Gdx.graphics.getHeight() / 10).prefWidth(Gdx.graphics.getWidth()).center();
        bottomContainer.row().fillX().fillY();
        bottomContainer.add(placeholderLabel).prefWidth(Gdx.graphics.getWidth());
        bottomContainer.add(buttonBack).prefWidth(Gdx.graphics.getHeight()/20 * 8).padLeft(Gdx.graphics.getWidth() / 20).padRight(Gdx.graphics.getWidth() / 20);
        bottomContainer.add(placeholderLabel).prefWidth(Gdx.graphics.getWidth());

        sprite = new Sprite(TextureManager.creditsBackground);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        batch = new SpriteBatch();



    }

    public void show (){

        buttonBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game, 0));
            }
        });
        Gdx.input.setInputProcessor(stage);
    }



    public void render (float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        sprite.draw(batch);
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
