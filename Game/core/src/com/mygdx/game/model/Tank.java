package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by annieaa on 10/03/15.
 */
public class Tank extends Vehicle {

    public Tank() {
        super(TextureManager.tank);
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.draw(getTexture(), position.x, position.y, (float) TextureManager.barrel.getWidth()/10, (float) TextureManager.barrel.getHeight()/10);

    }

}
