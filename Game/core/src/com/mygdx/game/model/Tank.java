package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by annieaa on 10/03/15.
 */
public class Tank extends Vehicle {

    private Environment environment;
    private Barrel barrel;

    public Tank(Environment environment) {
        super(TextureManager.tank);
        this.environment = environment;
        barrel = new Barrel(new Vector2(getPosition().x + TextureManager.tank.getWidth()/2, getPosition().y + TextureManager.tank.getHeight()/2), 45);
    }

}
