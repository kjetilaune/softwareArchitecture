package com.mygdx.game.controller;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.model.Barrel;
import com.mygdx.game.model.Vehicle;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 19.03.2015.
 */
public class AngleController implements EventListener{

    // models the controller wants to change/access
    private Vehicle vehicleModel;
    private Barrel barrelModel;

    public AngleController(Vehicle vehicleModel, Barrel barrelModel){
        this.vehicleModel = vehicleModel;
        this.barrelModel = barrelModel;
    }

    public boolean handle (Event event){

        // if the finger is on screen
        if (event.toString().equals("touchDown") || event.toString().equals("touchDragged")){
            // changes the angle of barrel
            changeAngle(vehicleModel, barrelModel, ((InputEvent)event).getStageX(), ((InputEvent)event).getStageY());
            return true;
        }

        return false;
    }

    // changes the angle of the barrel
    public void changeAngle(Vehicle vehicle, Barrel barrel, float x, float y) {
        // if out of deadzone (at bottom where buttons are)
        if (y > 200f) {
            barrel.setAngle(barrel.getAngle(x, y) * -1 - vehicle.getRotation() + 360);
        }
    }

}
