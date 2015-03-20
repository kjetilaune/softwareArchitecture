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

    public Tank(Environment environment, Vector2 position) {
        super(TextureManager.tank);
        this.environment = environment;
        super.setPosition(position);
        setRotation(environment.getAngle(getPosition().x, getPosition().x + TextureManager.tank.getWidth()));
        barrel = new Barrel(getBarrelPosition(), 45);
        barrel.setRotation(getRotation());
    }

    @Override
    public void setPosition(Vector2 position) {
        super.setPosition(position);
        barrel.setPosition(getBarrelPosition());
        barrel.setRotation(getRotation());
    }



    public Vector2 getBarrelPosition() {

        float barrelX = getPosition().x + TextureManager.tank.getWidth()/2;
        //float barrelY = environment.getGroundHeight(getPosition().x + TextureManager.tank.getWidth()/2);

        float barrelY = getPosition().y + TextureManager.tank.getHeight()/2;

        float newBarrelX = (float)(Math.cos(Math.toRadians(getRotation())) * (barrelX - getPosition().x) - Math.sin(Math.toRadians(getRotation())) * (barrelY - getPosition().y) + getPosition().x);
        float newBarrelY = (float)(Math.sin(Math.toRadians(getRotation())) * (barrelX - getPosition().x) - Math.cos(Math.toRadians(getRotation())) * (barrelY - getPosition().y) + getPosition().y);

        return new Vector2(newBarrelX, newBarrelY);
    }


    public Barrel getBarrel() {
        return barrel;
    }

}
