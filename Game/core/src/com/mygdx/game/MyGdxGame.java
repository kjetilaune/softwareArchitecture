package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.mygdx.game.view.MainMenu;

public class MyGdxGame extends Game implements ApplicationListener{

	SpriteBatch batch;
	Texture img;
    public Music introSong;
    public static int HEIGHT, WIDTH;


	@Override
	public void create () {

        introSong = Gdx.audio.newMusic(Gdx.files.internal("Music/introSong.mp3"));

        HEIGHT = Gdx.graphics.getHeight();
        WIDTH = Gdx.graphics.getWidth();

		batch = new SpriteBatch();
        setScreen(new MainMenu(this, 0));
	}

	/*@Override
	public void render () {
		//Gdx.gl.glClearColor(1, 0, 0, 1);
        //generateBackground();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
*/

}
