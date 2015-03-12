package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Mikal on 12.03.2015.
 */
public class F_Bomb extends Ammunition{

    public void render(SpriteBatch batch) {
        //does something
    }
    public F_Bomb(Integer initialDamage, Integer clusterCount, Integer blastRadius, Integer weight) {
        super("F-Bomb", initialDamage, clusterCount, blastRadius, weight);
    }

}
