package com.mygdx.game.view;

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
import com.mygdx.game.model.AudioVisualManagers.TextureManager;


/**
 * Created by annieaa on 11/03/15.
 */
public class InstructionsView implements Screen {

    MyGdxGame game;

    private Stage stage;
    private Table table;

    private BitmapFont font;

    private SpriteBatch batch;
    private Sprite aboutSprite;

    private Skin skin;


    private Label title, aboutText;
    private TextButton buttonMainMenu;

    public InstructionsView(MyGdxGame game) {

        this.game = game;

        stage = new Stage();
        table = new Table();

        skin = new Skin(Gdx.files.internal("skins/skin.json"), new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
        skin.getFont("font").setScale((float)(Gdx.graphics.getHeight()) / 540);



        font = new BitmapFont(Gdx.files.internal("font/fireBold.fnt"), Gdx.files.internal("font/fireBold.png"), false);
        batch = new SpriteBatch();

        title = new Label("About", skin);
        /*aboutText = new Label("- Move your tank with the arrows to the left or the right.\n" +
                "- Change the angle of the barrel using the up and down buttons.\n" +
                "- Toggle your ammo with the 'Change Ammo' button.\n" +
                "- Fire when you're ready.\n" +
                "- After each round you can buy new ammo and upgrades. ", skin);
        aboutText.setFontScale((float)0.99, (float)0.99);*/
        buttonMainMenu = new TextButton("Main Menu", skin);

        aboutSprite = new Sprite(TextureManager.aboutBackground);
        aboutSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        aboutSprite.draw(batch);
        batch.end();


        batch.begin();
        font.setScale((float)(Gdx.graphics.getHeight()) / 1080);
        font.draw(batch, "Move your tank with the arrows to the left or the right.", Gdx.graphics.getWidth() / 20 * 2, Gdx.graphics.getHeight() / 20 * 14);
        font.draw(batch, "Change the angle of the barrel by pressing the screen.", Gdx.graphics.getWidth() / 20 * 2, Gdx.graphics.getHeight() / 20 * 12);
        font.draw(batch, "Toggle through your ammo by pressing the Ammo-button.", Gdx.graphics.getWidth() / 20 * 2, Gdx.graphics.getHeight() / 20 * 10);
        font.draw(batch, "Hold the Fire-button to fluctuate power and release to fire.", Gdx.graphics.getWidth() / 20 * 2, Gdx.graphics.getHeight() / 20 * 8);
        font.draw(batch, "After each round you can buy new ammo.", Gdx.graphics.getWidth() / 20 * 2, Gdx.graphics.getHeight() / 20 * 6);
        batch.end();

        stage.act();
        stage.draw();

    }

    @Override
    public void show() {

        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game, 100));
            }
        });




        table.add(buttonMainMenu).size(500, 120);
        table.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() / 20 * 2);
        table.setFillParent(false);
        stage.addActor(table);



        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
