package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.gui.GameView;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class MovementController extends AbstractController implements EventListener{
    GameView view;
    boolean touchdown;
    MoveThread movement;
    public MovementController(GameView view){
        super(view);
        this.view = view;
        movement = new MoveThread();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public boolean touchDown(Event event, float x, float y,
                             int pointer, int button) {
        movement.move(event.getTarget().toString(), view.currentVehicle, view.environment);
        return true;
    }

    public boolean handle (Event event){

        if (event.toString() == "touchDown"){
            touchDown(event, event.getTarget().getX(), event.getTarget().getY(), 0, 0);
        }
        else if (event.toString() == "exit") {
            movement.end();
        }

        return true;
    }

}
