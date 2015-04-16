package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;
import com.mygdx.game.gui.SettingsView;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.Game;
import com.mygdx.game.model.GameSettings;
import com.mygdx.game.model.Player;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class SettingsController extends AbstractController implements EventListener {

    // the view the controller listens to
    private SettingsView view;

    private Environment environment;

    public SettingsController(AbstractView view){
        super(view);
        this.view = (SettingsView)view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void touchUp(InputEvent event) {

        GameSettings settings = new GameSettings();

        settings.setNofPlayers(view.numberOfPlayers);
        settings.setNumberOfRounds(view.numberOfRounds);
        settings.setNumberOfMoves(view.numberOfMoves);
        settings.setTeams(view.teams);

        view.game.setScreen(new GameView(view.game, new Game(settings)));


    }

    public boolean touchDown(InputEvent event) {

        return true;
    }


    public boolean handle (Event event){

        if (event.toString() == "touchUp"){
            touchUp((InputEvent)event);
        }
        return true;
    }




}
