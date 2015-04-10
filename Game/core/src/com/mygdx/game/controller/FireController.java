package com.mygdx.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;
import com.mygdx.game.model.BulletPhysics;
import com.mygdx.game.model.Tank;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class FireController extends AbstractController implements EventListener{

    // the view the controller listens to
    private GameView view;

    // the thread used for continuous movement
    private FireThread fireThread;

    public FireController(AbstractView view){
        super(view);
        this.view = (GameView)view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


    public boolean handle (Event event){

        if (event.toString().equals("touchDown")) {
            fireThread = new FireThread();
        }
        else if (event.toString().equals("touchUp")) {

            System.out.println("FIRE" + " X:" + ((InputEvent)event).getStageX() + " Y:" + ((InputEvent)event).getStageY());
            view.isFiring = true;
            view.currentPlayer.getChosenAmmo().setPosition(((Tank)view.currentVehicle).getBarrel().getTipOfBarrel());
            fireThread.fire(view.currentVehicle, view.currentPlayer.getChosenAmmo(), view.environment);
            view.gameInstance.changePlayer();

            // kill the power-fluctuation-thread

        }
        else if (event.toString().equals("enter")) {
            // start/continue power-fluctuation
        }

        else if (event.toString().equals("exit")) {
            // end/halt power-fluctuation
        }

        // has to return something
        return true;
    }

    // Mikal's attempt at power-fluctuation

    /*
    needs separate thread

    private int power;
    private boolean countUp;

    // touchDown starts power-fluctuation
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

        power = 0;
        countUp = true;

        // while finger is on Fire-button, fluctuate power
        while (isOver()) {
            if (power == 100) {
                countUp = false;
            }
            else if (power == 0) {
                countUp = true;
            }

            if (countUp) {
                power++;
            }
            else {
                power--;
            }

        }

    }
    */

}
