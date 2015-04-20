package com.mygdx.game.controller;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;
import com.mygdx.game.model.BulletPhysics;
import com.mygdx.game.model.SoundManager;
import com.mygdx.game.model.Tank;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class FireController extends AbstractController implements EventListener{

    // the view the controller listens to
    private GameView view;

    // the thread used for updating bullet position
    private FireThread fireThread;

    // the thread used for continuous movement
    private PowerThread powerThread;

    public FireController(AbstractView view){
        super(view);
        this.view = (GameView)view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


    public boolean handle (Event event){

        if (event.toString().equals("touchDown")) {
            powerThread = new PowerThread();
            return true;

        }
        else if (event.toString().equals("touchUp")) {

            // kills the power-fluctuation-thread
            powerThread.killThread();

            // if touchUp is registered outside firebutton-area
            if (view.outsideFireButton(((InputEvent)event).getStageX(), ((InputEvent)event).getStageY())) {
                view.currentVehicle.setPower(0.0f);
                return true;
            }

            //System.out.println("FIRE" + " X:" + ((InputEvent)event).getStageX() + " Y:" + ((InputEvent)event).getStageY());

            fireThread = new FireThread(view);

            //fireThread.fire(view, view.currentPlayer.getChosenAmmo(), view.environment);
            return true;
        }

        else if (event.toString().equals("enter")) {
            // start/continue power-fluctuation
            powerThread.initiateFluctuation(view.currentVehicle);
            return true;
        }

        else if (event.toString().equals("exit")) {
            // end/halt power-fluctuation
            powerThread.endFluctuation();
            return true;
        }

        return false;
    }

}
