package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Enums.Team;

/**
 * Created by Nixa on 3/12/2015.
 */

public class Barrel extends GameObject {
    private float angle;

    public Barrel(Team team, Vector2 rootPos, float angle) {
        super(team.getBarrelTexture());
        this.position = rootPos;
        this.angle = angle;
    }

    public void setAngle(float angle) {

        // works now I think!!

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

        if (newAngle > 180 && newAngle < 270) {
            this.angle = 180;
        }
        else if (newAngle > 270) {
            this.angle = 0;
        }
        else {
            this.angle = newAngle;
        }

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

        float s = (float)Math.sin((getAngle() + getRotation()) * Math.PI / 180);
        float c = (float)Math.cos((getAngle() + getRotation()) * Math.PI / 180);

        // translate point back to origin
        tipX -= bottomX;
        tipY -= bottomY;

        // rotate point
        float xNewBarrel = tipX * c - tipY * s;
        float yNewBarrel = tipX * s + tipY * c;

        // translate point back
        tipX = xNewBarrel + bottomX;
        tipY = yNewBarrel + bottomY;

        System.out.println("Tip of barrel is: " + tipX + ", " + tipY);


        return new Vector2(tipX, tipY);
    }


    public float getAngle() {
        return angle;
    }

}