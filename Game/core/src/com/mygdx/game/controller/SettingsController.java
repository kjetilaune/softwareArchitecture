package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;
import com.mygdx.game.gui.SettingsView;
import com.mygdx.game.model.Game;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class SettingsController extends AbstractController implements EventListener {

    // the view the controller listens to
    private SettingsView view;

    public SettingsController(AbstractView view){
        super(view);
        this.view = (SettingsView)view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        boolean touchdown = true;

        Game gameInstance = new Game();
        gameInstance.setNumberOfRounds(view.numberOfRounds);
        gameInstance.setRoundTime(view.roundTime);

        view.game.setScreen(new GameView(view.game, gameInstance));


    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        boolean touchdown=false;
        //do your stuff it will work when u touched your actor
        return true;
    }


    public boolean handle (Event event){

        if (event.toString() == "touchUp"){
            touchUp((InputEvent)event, event.getTarget().getX(), event.getTarget().getY(), 0, 0);
        }
        return true;
    }


}
