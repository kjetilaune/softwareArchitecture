package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jonathan on 10.03.2015.
 */
public class FireController {
    public float power;
    public float angle;
    public boolean fixedHorizontalDistance = true;
    boolean wasPressed;
    FireController controller;

    final Vector2 pressedPosition = new Vector2();
    final Vector2 currentPosition = new Vector2();
    final Vector2 tmp = new Vector2();

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


}
