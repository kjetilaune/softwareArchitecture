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
        this.angle = angle;
    }


    public float getAngle() {
        return angle;
    }

}