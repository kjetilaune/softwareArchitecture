package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Nixa on 3/12/2015.
 */

public class Barrel extends GameObject {
    private float angle;

    public Barrel(Vector2 rootPos, float angle) {
        super(TextureManager.barrel);
        this.position = rootPos;
        this.angle = angle;
    }

    public void setAngle(float angle) {

        // need to fix restrictions, they work most of the time, but not all of the time..
        
        /*System.out.println("want to set angle to: " + angle);
        System.out.println("rotation of barrel is: " + getRotation());*/

        if (angle < 0) {
            this.angle = 0;
        }
        else if (angle > 180) {
            this.angle = 180;
        }
        else {
            this.angle = angle;
        }

    }

    public float getAngle(float touchX, float touchY) {

        double opposite = getPosition().y - touchY;
        double adjacent = touchX - getPosition().x;
        double tan = opposite / adjacent;
        double degrees = Math.toDegrees(Math.atan(tan)); // I quadrant


        if (touchY <= getPosition().y) {
            if (touchX < getPosition().x) { // IL quadrant
                degrees += 180;
            }
        }
        else {
            if (touchX < getPosition().x) { //  III quadrant
                degrees += 180;
            }
            else { // IV quadrant
                degrees = 360 + degrees;
            }
        }

        return((float)degrees);

    }


    public float getAngle() {
        return angle;
    }

}