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



    @Override
    public void render(SpriteBatch batch) {

        float y = environment.getGroundHeight(position.x);


        batch.draw(new TextureRegion(getTexture()), position.x, position.y, position.x, position.y, (float) TextureManager.tank.getWidth(), (float) TextureManager.tank.getHeight(), 1, 1, environment.getAngle(position.x, position.x+TextureManager.tank.getWidth()));


    }

}
