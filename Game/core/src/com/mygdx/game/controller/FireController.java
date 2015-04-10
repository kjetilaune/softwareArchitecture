package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.gui.AbstractView;
import com.mygdx.game.gui.GameView;
import com.mygdx.game.model.BulletPhysics;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.Vehicle;

import java.beans.PropertyChangeEvent;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class FireController extends AbstractController implements EventListener{

    // the view the controller listens to
    GameView view;

    // the thread used for continuous movement
    FireThread firing;

    BulletPhysics physics;


    boolean wasPressed;
    FireController controller;

    final Vector2 pressedPosition = new Vector2();
    final Vector2 currentPosition = new Vector2();
    final Vector2 tmp = new Vector2();

    public FireController(GameView view){
        super(view);
        this.view = view;
        firing = new FireThread();
    }

    public FireController(FireController controller, Vector2 position){
        this.controller = controller;
        wasPressed = false;
        this.pressedPosition.set(position);
    }


    /* Updates in controller?
    public void update(float delta){
        if (Gdx.input.isTouched()){ //isButtonPressed (?)
            if (!wasPressed)
                wasPressed = true;
            currentPosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            tmp.set(currentPosition).sub(pressedPosition);
            controller.angle = tmp.angle();
        }
        else{
            if(wasPressed){
                wasPressed = false;
            }
        }
    }*/




    // Mikal's attempt at power-fluctuation

    /*
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

        boolean touchdown=true;

        //do your stuff
        //it will work when finger is released..
        System.out.println("FIRE" + " X:" + x + " Y:" + y);
        this.view.isFiring = true;
        view.currentPlayer.getChosenAmmo().setPosition(((Tank)view.currentVehicle).getBarrel().getTipOfBarrel());
        firing.move(view.currentVehicle, view.currentPlayer.getChosenAmmo(), view.environment);


    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

        boolean touchdown=false;

        //do your stuff it will work when u touched your actor

        return true;
    }

    public boolean handle (Event event){

        if (event.toString().equals("touchDown")) {
            touchDown((InputEvent)event, event.getTarget().getX(), event.getTarget().getY(), 0, 0);

        }
        else if (event.toString().equals("touchUp")) {
            touchUp((InputEvent)event, event.getTarget().getX(), event.getTarget().getY(), 0, 0);
        }

        return true;
    }

}
