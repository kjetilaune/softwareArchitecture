package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.controller.Threads.MoveThread;
import com.mygdx.game.model.Game;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class MovementController implements EventListener{

    // model the controller wants to change/access
    private Game gameModel;

    // the thread used for continuous movement
    private MoveThread moveThread;

    public MovementController(Game gameModel){
        this.gameModel = gameModel;
    }

    public boolean handle (Event event){

        if (event.toString().equals("touchDown")) {
            moveThread = new MoveThread();
            return true;
        }
        else if (event.toString().equals("touchUp")) {
            moveThread.killThread();
            return true;
        }
        // if button is pressed
        else if (event.toString().equals("enter")){
            // update the thread with the current direction, tank, and environment to initiate movement
            moveThread.initiateMovement(event.getTarget().toString(), gameModel.getCurrentPlayer().getVehicle(), gameModel.getEnvironment());
            return true;
        }
        // if the button is no longer pressed, either by lifting or moving the finger/cursor
        else if (event.toString().equals("exit")) {
            // end the movement
            moveThread.endMovement();
            return true;
        }

        return false;
    }

}
