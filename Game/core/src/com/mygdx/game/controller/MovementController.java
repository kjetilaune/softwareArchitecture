package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class MovementController extends AbstractController implements EventListener{
    GameView view;
    public MovementController(GameView view){
        super(view);
        this.view = view;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void touchUp(Event event, float x, float y,
                        int pointer, int button) {
        boolean touchdown=true;
        if (event.getTarget().toString().equals("arrowLeft")){
            this.view.tank.setPosition(new Vector2(view.tank.getPosition().x - 10,view.environment.getGroundHeight(view.tank.getPosition().x - 10)));
        }

    }

    public boolean touchDown(Event event, float x, float y,
                             int pointer, int button) {
        boolean touchdown=false;
        //do your stuff it will work when u touched your actor
        return true;
    }
    public boolean handle (Event event){

        if (event.toString() == "touchUp"){

            touchUp(event, event.getTarget().getX(), event.getTarget().getY(), 0, 0);
        }
        return true;
    }

}
