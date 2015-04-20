package com.mygdx.game.model.AmmoTypes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Ammunition;

/**
 * Created by Mikal on 12.03.2015.
 */
public class Instakill extends Ammunition {

    public void render(SpriteBatch batch) {
        //does something
    }

    public Instakill() {
        super("Instakill", 100, 1, 100, 10.0f);
    }
}