package com.mygdx.game.model.AmmoTypes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Ammunition;

/**
 * Created by Mikal on 12.03.2015.
 */
public class F_Bomb extends Ammunition {

    public void render(SpriteBatch batch) {
        //does something
    }
    public F_Bomb() {
        super("F-Bomb", 10, 1, 25, 3.0f);
    }

}
