package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Nixa on 3/12/2015.
 */

public class Barrel extends GameObject {
    private float angle;
    TextureRegion tr;
    public Barrel (Vector2 rootPos, float angle){
        super();
        this.position = rootPos;
        this.angle = angle;
        tr = new TextureRegion(TextureManager.barrel);
    }

    //@Override
    public void render(SpriteBatch batch) {
        batch.draw(tr, position.x, position.y, position.x, position.y, (float) TextureManager.barrel.getWidth(), (float) TextureManager.barrel.getHeight(), 1, 1, angle);
    }

public void setAngle(float angle){
    this.angle = angle;
    }
}
