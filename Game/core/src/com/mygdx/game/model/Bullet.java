package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Mikal on 12.03.2015.
 */
public class Bullet extends Ammunition{

    public void render(SpriteBatch batch) {
        //does something
    }
    public Bullet(Integer initialDamage, Integer clusterCount, Integer blastRadius, Integer weight) {
        super("Bullet", initialDamage, clusterCount, blastRadius, weight);
    }
}
