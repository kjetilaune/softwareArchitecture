package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class MovementController extends AbstractController implements EventListener{

    // the view the controller listens to
    private GameView view;

    // the thread used for continuous movement
    private MoveThread moveThread;

    public MovementController(AbstractView view){
        super(view);
        this.view = (GameView)view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public boolean handle (Event event){

        if (!event.toString().equals("touchDragged"))
            System.out.println(event.toString());

        if (event.toString().equals("touchDown")) {
            moveThread = new MoveThread();
        }
        else if (event.toString().equals("touchUp")) {
            moveThread.killThread();
        }
        // if button is pressed
        else if (event.toString().equals("enter")){
            // update the thread with the current direction, tank, and environment to initiate movement
            moveThread.initiateMovement(event.getTarget().toString(), view.currentVehicle, view.environment);
        }
        // if the button is no longer pressed, either by lifting or moving the finger/cursor
        else if (event.toString().equals("exit")) {
            // end the movement
            moveThread.endMovement();
        }

        // has to return something
        return true;
    }

}
