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
        barrel = new Barrel(new Vector2(getPosition().x + TextureManager.tank.getWidth()/2, getPosition().y + TextureManager.tank.getHeight()/2 - TextureManager.barrel.getHeight()/2), 45);
        barrel.setRotation(getRotation());
    }

    @Override
    public void setPosition(Vector2 position) {
        super.setPosition(position);
        //barrel.setPosition(getBarrelPosition());
        barrel.setPosition(new Vector2(getPosition().x + TextureManager.tank.getWidth()/2, getPosition().y + TextureManager.tank.getHeight()/2 - TextureManager.barrel.getHeight()/2));
        barrel.setRotation(getRotation());
    }

    public Barrel getBarrel() {
        return barrel;
    }

    public Vector2 getBarrelPosition() {

        //float barrelX = getPosition().x + TextureManager.tank.getWidth()/2;
        //float barrelY = getPosition().y + TextureManager.tank.getHeight()/2 - TextureManager.barrel.getHeight()/2;


        float newBarrelX = (float)(Math.cos(Math.toRadians(getRotation())) * (barrel.getPosition().x - getPosition().x) - Math.sin(Math.toRadians(getRotation())) * (barrel.getPosition().y - getPosition().y) + getPosition().x);
        float newBarrelY = (float)(Math.sin(Math.toRadians(getRotation())) * (barrel.getPosition().x - getPosition().x) - Math.cos(Math.toRadians(getRotation())) * (barrel.getPosition().y - getPosition().y) + getPosition().y);

        //System.out.println("old position: " + barrelX + ", " + barrelY);
        //System.out.println("new position: " + newBarrelX + ", " + newBarrelY);

        return new Vector2(newBarrelX, newBarrelY);
    }

}
