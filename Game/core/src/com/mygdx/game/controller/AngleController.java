package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;
import com.mygdx.game.model.Barrel;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.Vehicle;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 19.03.2015.
 */
public class AngleController extends AbstractController implements EventListener{

    // the view the controller listens to
    private GameView view;

    public AngleController(AbstractView view){
        super(view);
        this.view = (GameView)view;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


    public boolean handle (Event event){

        // if the finger is on screen
        if (event.toString().equals("touchDown") || event.toString().equals("touchDragged")){
            // changes the angle of barrel
            changeAngle(view.currentVehicle, ((Tank)view.currentVehicle).getBarrel(), ((InputEvent)event).getStageX(), ((InputEvent)event).getStageY());
        }

        // has to return something
        return true;
    }


    // changes the angle of the barrel
    public void changeAngle(Vehicle vehicle, Barrel barrel, float x, float y) {
        // if out of deadzone (at bottom and top where buttons are. should be properly generalized)
        if (y > 200f && y < 935f) {

            System.out.println("Barrel angle: " + Float.toString(barrel.getAngle(x, y)));
            System.out.println("Vehicle rotation: " + Float.toString(vehicle.getRotation()));
            System.out.println("Total: " + Float.toString(barrel.getAngle(x, y) * -1 - vehicle.getRotation() + 360) + "\n");

            barrel.setAngle(barrel.getAngle(x, y) * -1 - vehicle.getRotation() + 360);

        }
    }

}
