package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.controller.Threads.MoveThread;
import com.mygdx.game.model.Environment;
import com.mygdx.game.model.Vehicle;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class MovementController implements EventListener{

    // models the controller wants to change/access
    private Vehicle vehicleModel;
    private Environment environmentModel;

    // the thread used for continuous movement
    private MoveThread moveThread;

    public MovementController(Vehicle vehicleModel, Environment environmentModel){
        this.vehicleModel = vehicleModel;
        this.environmentModel = environmentModel;
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
            moveThread.initiateMovement(event.getTarget().toString(), vehicleModel, environmentModel);
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
