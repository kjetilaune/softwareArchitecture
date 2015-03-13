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


}
