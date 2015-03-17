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
    private int a;

    public Tank(Environment environment) {
        super(TextureManager.tank);
        this.environment = environment;
        a = 0;
    }

}
