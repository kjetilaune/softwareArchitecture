package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.gui.GameView;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class MovementController extends AbstractController implements EventListener{

    // the view the controller listens to
    GameView view;

    // the thread used for continuous movement
    MoveThread movement;

    public MovementController(GameView view){
        super(view);
        this.view = view;
        movement = new MoveThread();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public boolean handle (Event event){

        // if button is pressed
        if (event.toString().equals("touchDown")){
            // update the thread with the current direction, tank, and environment to initiate movement
            movement.move(event.getTarget().toString(), view.currentVehicle, view.environment);
        }
        // if the button is no longer pressed, either by lifting or moving the finger/cursor
        else if (event.toString().equals("exit")) {
            // end the movement
            movement.endMovement();
        }

        // has to return something
        return true;
    }

}
