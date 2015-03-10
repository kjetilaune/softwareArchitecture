package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class MainScreen implements Screen {
    private Stage stage = new Stage();

    /*
    * What we need to implement
    *
    * Buttons:
    * - One Fire-button
    * - One Ammo changer-button
    * - Two movement arrow (one in either direction)
    *
    * Information showing:
    * - Current ammo chosen
    * - Current stockpile of chosen ammo
    * - Amount of health
    * - Amount of fuel
    * - Amount of armor
    * - Power bar (fluctuates when Fire-button is pressed)
    * - Angle crosshair
    *
    * */

    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();


    }
    public void resize(int width, int height){

    }
    public void show(){

    }
    public void hide(){
        dispose();
    }
    public void pause(){

    }
    public void resume(){}

    public void dispose(){
        stage.dispose();
    }

}
