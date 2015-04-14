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

        //System.out.println("want to set angle to: " + angle);
        //System.out.println("rotation of barrel is: " + getRotation());

        float newAngle = angle;

        while (newAngle > 360) {
            newAngle -= 360;
        }

        while (newAngle < 0) {
            newAngle += 360;
        }

        System.out.println("Setting angle of barrel to " + newAngle);

        this.angle = newAngle;

        /*if (angle < 0) {
            this.angle = 0;
        }
        else if (angle > 180) {
            this.angle = 180;
        }
        else {
            this.angle = angle;
        }*/

    }

    public float getAngle(float touchX, float touchY) {

        double opposite = getPosition().y - touchY;
        double adjacent = touchX - getPosition().x;
        double tan = opposite / adjacent;
        double degrees = Math.toDegrees(Math.atan(tan)); // I quadrant


        if (touchY <= getPosition().y) {
            if (touchX < getPosition().x) { // II quadrant
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

    public Vector2 getTipOfBarrel() {

        float bottomX = getPosition().x;
        float bottomY = getPosition().y;
        float tipX = bottomX + TextureManager.barrel.getWidth();
        float tipY = bottomY + TextureManager.barrel.getHeight()/2;

        float s = (float)Math.sin(getAngle() + getRotation() * Math.PI / 180);
        float c = (float)Math.cos(getAngle() + getRotation() * Math.PI / 180);

        // translate point back to origin
        tipX -= bottomX;
        tipY -= bottomY;

        // rotate point
        float xNewBarrel = tipX * c - tipY * s;
        float yNewBarrel = tipX * s + tipY * c;

        // translate point back
        tipX = xNewBarrel + bottomX;
        tipY = yNewBarrel + bottomY;

        return new Vector2(tipX, tipY);
    }


    public float getAngle() {
        return angle;
    }

}