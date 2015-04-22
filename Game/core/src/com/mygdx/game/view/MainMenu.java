package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
import com.mygdx.game.model.AudioVisualManagers.SoundManager;
import com.mygdx.game.model.AudioVisualManagers.TextureManager;
import com.mygdx.game.model.Store;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class MainMenu implements Screen {

    MyGdxGame game;

    private Stage stage;
    private Table table;

    private Skin skin;
    private Label title;
    private TextButton buttonNewGame;
    //private TextButton buttonSettings;
    private TextButton buttonAbout;
    private TextButton buttonCredits;

    private Sprite titleSprite;
    private Sprite menuSprite;
    private SpriteBatch batch;

    private int clock;
    //public Music introSong;
    public Sound foodFrenzy;


    public MainMenu(MyGdxGame game, int clock) {

        this.game = game;

        stage = new Stage();
        table = new Table();

        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));

        skin.get("default", TextButton.TextButtonStyle.class).font.setScale(2f);

        foodFrenzy = SoundManager.foodFrenzy;
        //introSong = Gdx.audio.newMusic(Gdx.files.internal("Music/introSong.mp3"));


        buttonNewGame = new TextButton("New Game", skin);
        //buttonSettings = new TextButton("Settings", skin);
        buttonAbout = new TextButton("Instructions", skin);
        buttonCredits = new TextButton("Credits", skin);

        titleSprite = new Sprite(TextureManager.titleBackground);
        titleSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        menuSprite = new Sprite(TextureManager.menuBackground);
        menuSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();
        this.clock = clock;



    }

    public void render(float delta){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (clock < 100) {
            titleSprite.draw(batch);
            clock ++;
            batch.end();

            if (clock > 7 && !game.introSong.isPlaying()) {
                game.introSong.play();
                game.introSong.setLooping(true);
            }

        }
        else {
            if(!game.introSong.isPlaying()){
                game.introSong.play();
                game.introSong.setLooping(true);
            }
            menuSprite.draw(batch);
            batch.end();
            stage.act();
            stage.draw();
        }






    }

    public void show(){

        foodFrenzy.play();

        /*buttonNewGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameView(game, new Game()));
            }
        });*/

        buttonNewGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsView(game));
            }
        });

        /*
        buttonSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsView(game));
            }
        });*/

        buttonAbout.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InstructionsView(game));
            }
        });

        buttonCredits.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new CreditsView(game));
            }
        });


        table.add(title).padBottom(40).row();
        table.add(buttonNewGame).size((int)(Gdx.graphics.getWidth()/2.8), (int)(Gdx.graphics.getHeight()/12)).padBottom(20).row();
        //table.add(buttonSettings).size(300, 120).padBottom(20).row();
        table.add(buttonAbout).size((int)(Gdx.graphics.getWidth()/2.8), (int)(Gdx.graphics.getHeight()/12)).padBottom(20).row();
        table.add(buttonCredits).size((int)(Gdx.graphics.getWidth()/2.8), (int)(Gdx.graphics.getHeight()/12)).padBottom(20).row();

        table.setFillParent(true);
        stage.addActor(table);


        Gdx.input.setInputProcessor(stage);

    }

    public void resize(int width, int height){
        stage.getViewport().update(width, height);
    }

    public void hide(){
        dispose();
    }

    public void pause(){

    }
    public void resume(){}

    public void dispose(){
        stage.dispose();
        skin.dispose();
    }

}
